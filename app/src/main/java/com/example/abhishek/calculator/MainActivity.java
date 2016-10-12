package com.example.abhishek.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Button> buttons = new ArrayList<Button>();
    private Button enter, clear, exit;
    private EditText output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (EditText) findViewById(R.id.editText);


        buttons.add((Button) findViewById(R.id.zero));
        buttons.add((Button) findViewById(R.id.one));
        buttons.add((Button) findViewById(R.id.two));
        buttons.add((Button) findViewById(R.id.three));
        buttons.add((Button) findViewById(R.id.four));
        buttons.add((Button) findViewById(R.id.five));
        buttons.add((Button) findViewById(R.id.six));
        buttons.add((Button) findViewById(R.id.seven));
        buttons.add((Button) findViewById(R.id.eight));
        buttons.add((Button) findViewById(R.id.nine));
        buttons.add((Button) findViewById(R.id.add));
        buttons.add((Button) findViewById(R.id.subtract));


        enter = (Button) findViewById(R.id.enter);
        clear = (Button) findViewById(R.id.clear);
        exit = (Button) findViewById(R.id.exit);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateAndSetOutput();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });


        for (final Button b : buttons) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onButtonClick(b.getId());
                }
            });
        }

    }

    private void onButtonClick(int id) {
        char c = buttonsEnum.valueOf(getResources().getResourceEntryName(id)).getValue();
        String toOutput = output.getText() + Character.toString(c);
        output.setText(toOutput);
    }

    private void calculateAndSetOutput() {
        char[] charArray = output.getText().toString().toCharArray();
        ArrayList<Integer> positionsOfOperators = new ArrayList<Integer>();

        for (int i = 0; i < charArray.length;i++) {
            if (charArray[i] == '+' || charArray[i] == '-')
                positionsOfOperators.add(i);
        }

        int newOutput = 0;
        for (int i = 0; i < positionsOfOperators.size();i++) {
            String number = "";
            if (i == 0) {
                String tempnum = "";
                for (int j = 0; j < positionsOfOperators.get(i); j++) {
                    tempnum += charArray[j];
                }
                newOutput = Integer.parseInt(tempnum);
            }

            for (int j = positionsOfOperators.get(i)+1; j < (positionsOfOperators.size() > i+1 ? positionsOfOperators.get(i+1) : charArray.length); j++) {
                number += charArray[j];
            }

            if (charArray[positionsOfOperators.get(i)] == '+') {
                newOutput += Integer.parseInt(number);
            } else if (charArray[positionsOfOperators.get(i)] == '-') {
                newOutput -= Integer.parseInt(number);
            }
        }

        output.setText(Integer.toString(newOutput));
    }

    private void clear() {
        output.setText("");
    }
    private void exit() {
        System.exit(0);
    }


    enum buttonsEnum {
        one('1'), two('2'), three('3'), four('4'), five('5'), six('6'), seven('7'), eight('8'), nine('9'), zero('0'), add('+'), subtract('-');

        private char inRealForm;
        buttonsEnum(char inRealForm) {
            this.inRealForm = inRealForm;
        }
        public char getValue() {
            return inRealForm;
        }
    }
}
