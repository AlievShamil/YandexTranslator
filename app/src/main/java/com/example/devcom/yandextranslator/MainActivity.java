package com.example.devcom.yandextranslator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private TextView mOutputView;
    private EditText mInputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mTranslateBtn = findViewById(R.id.btnTranslate);
        mOutputView = findViewById(R.id.tvTransfer);
        mInputView = findViewById(R.id.etInput);

//        mInputView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//               translate(editable.toString());
//            }
//        });

        mTranslateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translate(mInputView.getText().toString());
            }
        });
    }


    private void translate(String text) {
        Translator translator = new Translator();
        try {
            translator.execute(text, "ru");
            mOutputView.setText(translator.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
