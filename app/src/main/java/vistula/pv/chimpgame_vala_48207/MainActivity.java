package vistula.pv.chimpgame_vala_48207;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtViewResult_pv;
    Button btn_random_pv,btn_startgame_pv,btn_result_pv;
    String strNumber_pv,strResult_pv;

    Button[][] butony;
    int butonyID[][];
    int  left_pv, top_pv, totalButtons_pv, result_pv,How_Much_Buton_pv;
    int[] number_pv;
    int[] numberAfterPermutation_pv;
    int[] playerNumber;
    int currentButton;
    ConstraintLayout constraintLayout;
    ConstraintLayout.LayoutParams params;
    ConstraintSet set;
    public ArrayPermutation_pv ArrayPermutation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = (ConstraintLayout)findViewById(R.id.constraintLayout_pv);
        left_pv = 200;
        top_pv = 200;
        How_Much_Buton_pv = 3;
        totalButtons_pv = How_Much_Buton_pv*How_Much_Buton_pv;
        number_pv = new int[totalButtons_pv];
        for (int k = 0; k<totalButtons_pv; k++){
            number_pv[k] = k+1;
        }
        numberAfterPermutation_pv = number_pv;
        playerNumber = new int[totalButtons_pv];
        for (int k = 0; k<totalButtons_pv; k++){
            playerNumber[k] = -1;
        }
        butony = new Button[How_Much_Buton_pv][How_Much_Buton_pv];
        butonyID = new int[How_Much_Buton_pv][How_Much_Buton_pv];
        startButony();
        result_pv = 0;
    }

    public void startButony(){
        for (int i = 0; i < How_Much_Buton_pv; i++){
            for (int j = 0; j < How_Much_Buton_pv; j++){
                butony[i][j] = new Button(this);
                butony[i][j].setId(View.generateViewId());
                butonyID[i][j] = butony[i][j].getId();
                params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                butony[i][j].setLayoutParams(params);
                constraintLayout.addView(butony[i][j]);
                set = new ConstraintSet();
                set.clone(constraintLayout);
                if (j==0){
                    set.connect(butony[i][j].getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, left_pv);
                }else {
                    set.connect(butony[i][j].getId(), ConstraintSet.LEFT, butony[i][j - 1].getId(), ConstraintSet.RIGHT, 0);
                }
                if (i == 0){
                    set.connect(butony[i][j].getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, top_pv);
                }else {
                    set.connect(butony[i][j].getId(), ConstraintSet.TOP, butony[i - 1][j].getId(), ConstraintSet.BOTTOM, 0);
                }
                set.applyTo(constraintLayout);
                butony[i][j].setOnClickListener(buttonClickListener);
            }
        }
        liczbyButony();
    }

    final View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button)v;
            if (currentButton < totalButtons_pv){
                b.setText(Integer.toString(++currentButton));
            }
        }
    };

    private void liczbyButony(){
        String str;
        for (int i=0; i<How_Much_Buton_pv; i++){
            for (int j=0; j<How_Much_Buton_pv; j++){
                str = Integer.toString(numberAfterPermutation_pv[i*How_Much_Buton_pv+j]);
                butony[i][j].setText(str);
            }
        }
    }

    public void startbutton_pv(View view){
        for (int i=0; i<How_Much_Buton_pv; i++){
            for (int j=0; j<How_Much_Buton_pv; j++){
                butony[i][j].setText("*");
            }
        }
        currentButton = 0;
    }

    public void makePermuatation(View view){
        numberAfterPermutation_pv = ArrayPermutation.shuffleFisherYeats(number_pv);
        liczbyButony();
    }

    public void Result_pv(View view){
        Button button;
        result_pv = 0;

        for (int i=0; i<How_Much_Buton_pv;i++){
            for (int j=0; j<How_Much_Buton_pv; j++){
                button = (Button)findViewById(butonyID[i][j]);
                strNumber_pv = button.getText().toString();
                playerNumber[i*How_Much_Buton_pv+j] = Integer.parseInt(strNumber_pv);
                result_pv += (playerNumber[i*How_Much_Buton_pv+j] == numberAfterPermutation_pv[i*How_Much_Buton_pv+j])?1:-1;
            }
        }
        for (int i=0; i<How_Much_Buton_pv; i++){
            for (int j=0; j<How_Much_Buton_pv; j++){
                button = (Button)findViewById(butonyID[i][j]);
                 strResult_pv = Integer.toString(numberAfterPermutation_pv[i*How_Much_Buton_pv+j]) + "," +Integer.toString(playerNumber[i*How_Much_Buton_pv+j]);
                button.setText(strResult_pv);
            }
        }



        txtViewResult_pv = (TextView)findViewById(R.id.txtViewResult_pv);
        txtViewResult_pv.setText(Integer.toString(result_pv));
    }

}