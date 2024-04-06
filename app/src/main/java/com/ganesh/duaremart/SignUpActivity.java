package com.ganesh.duaremart;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ganesh.duaremart.ModelResponce.SignUpResponse;
import com.ganesh.duaremart.ModelResponce.VerifyOtpSignupResponse;
import com.ganesh.duaremart.Network.RetrofitClint;
import com.ganesh.duaremart.databinding.ActivitySignUpBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    String state = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
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

        setSpinnerData();
        setListener();


    }

    private void setListener() {
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidUserDetails()){
                    signUp();
                }
            }
        });
        binding.textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void signUp() {
        loading(true);
        String name,phone,email,address,state_id,pinCode,password;
        name = binding.edtFullName.getText().toString().trim();
        phone = binding.edtPhoneNumber.getText().toString().trim();
        email = binding.edtEmail.getText().toString().trim();
        state_id = state;
        address = binding.edtAddress.getText().toString().trim();
        pinCode = binding.edtPinCode.getText().toString().trim();
        password = binding.edtCreatePassword.getText().toString().trim();

        Call<SignUpResponse> call = RetrofitClint
                .getInstance()
                .getApi()
                .signup(name,phone,email,password,address,pinCode,"abcd",state_id,"","","","");
        call.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                SignUpResponse signUpResponse = response.body();
                if (response.isSuccessful()){
                    loading(false);
                    showBottomSheetDialog(signUpResponse);
                }
                else {
                    Toast.makeText(SignUpActivity.this,"Failed to SignIn", Toast.LENGTH_SHORT).show();
                    loading(false);
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "Error occurred while SignIn", Toast.LENGTH_SHORT).show();
                loading(false);
            }
        });

    }

    private void showBottomSheetDialog(SignUpResponse signUpResponse) {
        TextView textNotification;
        BottomSheetDialog dialog = new BottomSheetDialog(SignUpActivity.this);
        dialog.setContentView(R.layout.buttom_sheet_dialog_otp_verification);
        textNotification = dialog.findViewById(R.id.textNotification);
        textNotification.setText("Note: you will get OTP via Notification(" + signUpResponse.getData().getUser_otp1() + ")");
        dialog.show();

        dialog.findViewById(R.id.buttonOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtOtpVerification = dialog.findViewById(R.id.edtOtpVerification);
                if (edtOtpVerification.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                } else {
//                    VerifyOptSignupRequestData requestData = new VerifyOptSignupRequestData(signUpResponse.getData().getUser_id(), signUpResponse.getData().getUser_otp1());

                    Call<VerifyOtpSignupResponse> call = RetrofitClint
                            .getInstance()
                            .getApi()
                            .verify_otp_signup(signUpResponse.getData().getUser_id(), edtOtpVerification.getText().toString().trim());
                    call.enqueue(new Callback<VerifyOtpSignupResponse>() {
                        @Override
                        public void onResponse(Call<VerifyOtpSignupResponse> call, Response<VerifyOtpSignupResponse> response) {
                            if (response.isSuccessful() && response.body()!=null) {
                                VerifyOtpSignupResponse verifyOtpSignupResponse = response.body();
                                Toast.makeText(SignUpActivity.this, verifyOtpSignupResponse.getData(), Toast.LENGTH_SHORT).show();
                                if (Objects.equals(verifyOtpSignupResponse.getStatus(), "success")){
                                    finish();
                                }
                            } else {
                                Toast.makeText(SignUpActivity.this, "Failed to verify OTP", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<VerifyOtpSignupResponse> call, Throwable t) {
                            Toast.makeText(SignUpActivity.this, "Error occurred while verifying OTP", Toast.LENGTH_SHORT).show();
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


    private boolean isValidUserDetails() {
        if (binding.edtFullName.getText().toString().trim().isEmpty()){
            binding.edtFullName.requestFocus();
            binding.edtFullName.setError("Enter Name");
            return false;
        }else if (binding.edtPhoneNumber.getText().toString().trim().isEmpty()){
            binding.edtPhoneNumber.requestFocus();
            binding.edtPhoneNumber.setError("Enter Phone Number");
            return false;
        }else if (binding.edtPhoneNumber.getText().toString().trim().length()!=10){
            binding.edtPhoneNumber.requestFocus();
            binding.edtPhoneNumber.setError("Enter Valid Number");
            return false;
        }else if (binding.edtEmail.getText().toString().trim().isEmpty()){
            binding.edtEmail.requestFocus();
            binding.edtEmail.setError("Enter Email");
            return false;
        }else if (!binding.edtEmail.getText().toString().trim().contains("@")){
            binding.edtEmail.requestFocus();
            binding.edtEmail.setError("Enter Valid Email");
            return false;
        }else if (binding.edtAddress.getText().toString().trim().isEmpty()){
            binding.edtAddress.requestFocus();
            binding.edtAddress.setError("Enter Address");
            return false;
        }else if (binding.edtPinCode.getText().toString().trim().isEmpty()){
            binding.edtPinCode.requestFocus();
            binding.edtPinCode.setError("Enter Pin Code");
            return false;
        }else if (binding.edtPinCode.getText().toString().trim().length()!=6){
            binding.edtPinCode.requestFocus();
            binding.edtPinCode.setError("Enter Valid Pin Code");
            return false;
        }else if (binding.edtCreatePassword.getText().toString().trim().isEmpty()){
            binding.edtCreatePassword.requestFocus();
            binding.edtCreatePassword.setError("Enter Password");
            return false;
        }else if (binding.edtCreatePassword.getText().toString().trim().length()<4){
            binding.edtCreatePassword.requestFocus();
            binding.edtCreatePassword.setError("Password Must be Greater the 4");
            return false;
        }else if (binding.edtConfirmPassword.getText().toString().trim().isEmpty()){
            binding.edtConfirmPassword.requestFocus();
            binding.edtConfirmPassword.setError("Confirm Your Password");
            return false;
        }else if (!binding.edtCreatePassword.getText().toString().trim().equals(binding.edtConfirmPassword.getText().toString().trim())){
            Toast.makeText(this, "Password & Confirm Password Must be Same", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    private void setSpinnerData() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Andaman & Nicobar Islands");
        arrayList.add("Andhra Pradesh");
        arrayList.add("Arunachal Pradesh");
        arrayList.add("Assam");
        arrayList.add("Bihar");
        arrayList.add("Chandigarh");
        arrayList.add("Chhattisgarh");
        arrayList.add("Dadra & Nagar Haveli");
        arrayList.add("Daman & Diu");
        arrayList.add("Delhi");
        arrayList.add("Goa");
        arrayList.add("Gujarat");
        arrayList.add("Haryana");
        arrayList.add("Jammu & Kashmir");
        arrayList.add("Jharkhand");
        arrayList.add("Karnataka");
        arrayList.add("Kerala");
        arrayList.add("Lakshadweep");
        arrayList.add("Madhya Pradesh");
        arrayList.add("Maharashtra");
        arrayList.add("Manipur");
        arrayList.add("Meghalaya");
        arrayList.add("Mizoram");
        arrayList.add("Nagaland");
        arrayList.add("Odisha");
        arrayList.add("Puducherry");
        arrayList.add("Punjab");
        arrayList.add("Rajasthan");
        arrayList.add("Sikkim");
        arrayList.add("Tamil Nadu");
        arrayList.add("Tripura");
        arrayList.add("Uttar Pradesh");
        arrayList.add("Uttarakhand");
        arrayList.add("West Bengal");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        binding.spinner.setAdapter(adapter);
    }
    private void loading(boolean isLoading){
        if (isLoading){
            binding.progressBar.setVisibility(View.VISIBLE);
            binding.buttonSignUp.setVisibility(View.GONE);
        }else {
            binding.progressBar.setVisibility(View.GONE);
            binding.buttonSignUp.setVisibility(View.VISIBLE);
        }
    }
}