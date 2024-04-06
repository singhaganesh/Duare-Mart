package com.ganesh.duaremart;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.ganesh.duaremart.ModelResponce.LogInResponse;
import com.ganesh.duaremart.ModelResponce.VerifyOtpLoginResponse;
import com.ganesh.duaremart.Network.RetrofitClint;
import com.ganesh.duaremart.Storage.PreferenceManager;
import com.ganesh.duaremart.databinding.ActivityLoginBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Set status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        }
            setListener();
    }

    private void setListener() {
        binding.buttonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidUserDetails()){
                    login();
                }
            }
        });
        binding.textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSignUpActivity();
            }
        });
    }

    private void gotoSignUpActivity() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    private void login() {
        loading(true);
        String phoneNumber = binding.edtPhoneNumber.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();

        Call<LogInResponse> call = RetrofitClint
                .getInstance()
                .getApi()
                .login(phoneNumber,password,"123456");
        call.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if (response.isSuccessful() && response.body()!=null) {
                    LogInResponse logInResponse = response.body();
                    loading(false);
                    showBottomSheetDialog(logInResponse);
                } else {
                    Toast.makeText(LoginActivity.this, "Failed to LogIn", Toast.LENGTH_SHORT).show();
                    loading(false);
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error occurred while LogIn", Toast.LENGTH_SHORT).show();
                loading(false);
            }
        });
    }

    private void showBottomSheetDialog(LogInResponse logInResponse) {
        TextView textNotification;
        BottomSheetDialog dialog = new BottomSheetDialog(LoginActivity.this);
        dialog.setContentView(R.layout.buttom_sheet_dialog_otp_verification);
        textNotification = dialog.findViewById(R.id.textNotification);
        textNotification.setText("Note: you will get OTP via Notification(" + logInResponse.getData().getUser_otp1() + ")");
        dialog.show();

        dialog.findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtOtpVerification = dialog.findViewById(R.id.edtOtpVerification);
                if (edtOtpVerification.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                } else {

                    Call<VerifyOtpLoginResponse> call = RetrofitClint
                            .getInstance()
                            .getApi()
                            .verify_otp_login(logInResponse.getData().getUser_id(),edtOtpVerification.getText().toString().trim());
                    call.enqueue(new Callback<VerifyOtpLoginResponse>() {
                        @Override
                        public void onResponse(Call<VerifyOtpLoginResponse> call, Response<VerifyOtpLoginResponse> response) {
                            if (response.isSuccessful() && response.body()!=null) {

                                VerifyOtpLoginResponse verifyOtpLoginResponse = response.body();
                                if (Objects.equals(verifyOtpLoginResponse.getStatus(), "success")){
                                    Toast.makeText(LoginActivity.this, "Successfully LogIn", Toast.LENGTH_SHORT).show();
                                    goToMainActivity();
                                }

                            } else {
                                Toast.makeText(LoginActivity.this, "Failed to verify OTP", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<VerifyOtpLoginResponse> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "Error occurred while verifying OTP", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        dialog.findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void goToMainActivity() {
        PreferenceManager preferenceManager = new PreferenceManager(LoginActivity.this);
        preferenceManager.putBoolean("isLogin",true);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private boolean isValidUserDetails() {
        if (binding.edtPhoneNumber.getText().toString().trim().isEmpty()){
            binding.edtPhoneNumber.requestFocus();
            binding.edtPhoneNumber.setError("Enter Phone Number");
            return false;
        }else if (binding.edtPhoneNumber.getText().toString().trim().length()!=10){
            binding.edtPhoneNumber.requestFocus();
            binding.edtPhoneNumber.setError("Enter Valid Number");
            return false;
        }else if (binding.edtPassword.getText().toString().trim().isEmpty()){
            binding.edtPassword.requestFocus();
            binding.edtPassword.setError("Enter Password");
            return false;
        }else if (binding.edtPassword.getText().toString().trim().length()<4){
            binding.edtPassword.requestFocus();
            binding.edtPassword.setError("Password Must be Greater the 4");
            return false;
        }else {
            return true;
        }
    }
    private void loading(boolean isLoading){
        if (isLoading){
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.buttonLogIn.setVisibility(View.GONE);
        }else {
            binding.progressBar.setVisibility(View.GONE);
            binding.buttonLogIn.setVisibility(View.VISIBLE);
        }
    }
}