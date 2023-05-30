package com.example.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    public SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "my_prefs";
    private static final String KEY_USER_NAME = "user_name";

    private ActivityResultLauncher<Intent> nameActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.lab3.R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedName = sharedPreferences.getString(KEY_USER_NAME, "");
        editTextName.setText(savedName);

        Button nextButton = findViewById(R.id.buttonNext);
        nextButton.setOnClickListener(v -> {
            String name = editTextName.getText().toString();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_USER_NAME, name);
            editor.apply();

            Intent intent = new Intent(MainActivity.this, NameActivity.class);
            intent.putExtra(NameActivity.EXTRA_NAME, name);
            nameActivityResultLauncher.launch(intent);
        });

        nameActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            int resultCodeValue = data.getIntExtra("result_code", -1);
                            handleNameActivityResult(resultCodeValue);
                        }
                    }
                }
        );
    }

    private void handleNameActivityResult(int resultCode) {
        if (resultCode == 0) {


        } else if (resultCode == 1) {
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        String name = editTextName.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_NAME, name);
        editor.apply();
    }

}
