package com.example.asr_gameproject_1;


import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameactive = true;
    int activeplayer=0;
    int [] gamestate={2,2,2,2,2,2,2,2,2};
    int [][] winPositions= {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    public void choose(View view){
        ImageView img=(ImageView) view;
        int tappedimage = Integer.parseInt(img.getTag().toString());
        if(!gameactive){
            gamereset(view);
        }
        if(gamestate[tappedimage]==2 ) {
            gamestate[tappedimage] = activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer == 0) {
                img.setImageResource(R.drawable.o);
                activeplayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.x);
                activeplayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winPosition: winPositions){
            if(gamestate[winPosition[0]]==gamestate[winPosition[1]] &&
                    gamestate[winPosition[1]]==gamestate[winPosition[2]]
                    && gamestate[winPosition[0]]!=2){
                String winnerStr;
                gameactive=false;
                if(gamestate[winPosition[0]]==0){
                    winnerStr = "O has won";
                    MediaPlayer o_won=MediaPlayer.create(MainActivity.this,R.raw.o1);
                    o_won.start();
                }
                else{
                    winnerStr = "X has won";
                    MediaPlayer x_won=MediaPlayer.create(MainActivity.this,R.raw.x1);
                    x_won.start();
                }
                TextView status =findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }

    }
    public void gamereset(View view) {
        gameactive=true;
        activeplayer=0;
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView20)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("O's Turn - Tap to play");

    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}