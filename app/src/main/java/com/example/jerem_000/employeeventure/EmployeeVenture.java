package com.example.jerem_000.employeeventure;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;


public class EmployeeVenture extends ActionBarActivity {

    Button buttonUpdate, buttonFastFood, buttonWareHouse, buttonBank, buttonProgrammer;
    Button buttonProfitTimes, buttonPrestige, buttonMusic;
    private TextView actualWealth, actual_wealth_per_second, actualFFEmployees, actualWHEmployees,
    actualBEmployees, actualPEmployees;

    MediaPlayer player;

    private double wealth = 100.0;
    private int fastFoodEmployees = 0, fastFoodCost = 10;
    private int wareHouseEmployees = 0, wareHouseCost = 200;
    private int bankEmployees = 0, bankCost = 500;
    private int programmers = 0, programmerCost = 2500;
    private int multiplier = 1, Prestige = 0;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_venture);


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);

        buttonFastFood = (Button) findViewById(R.id.buttonFastFood);
        actualFFEmployees = (TextView) findViewById(R.id.ffemployees_view);

        buttonWareHouse = (Button) findViewById(R.id.buttonWareHouse);
        actualWHEmployees = (TextView) findViewById(R.id.whemployees_view);

        buttonBank = (Button) findViewById(R.id.buttonBank);
        actualBEmployees = (TextView) findViewById(R.id.bemployees_view);

        buttonProgrammer = (Button) findViewById(R.id.buttonProgrammer);
        actualPEmployees = (TextView) findViewById(R.id.pemployees_view);

        buttonProfitTimes = (Button) findViewById(R.id.buttonTimesProfit);

        buttonPrestige = (Button) findViewById(R.id.buttonPrestige);

        buttonMusic = (Button) findViewById(R.id.buttonMusic);
        player = MediaPlayer.create(EmployeeVenture.this, R.raw.def);

        actualWealth = (TextView) findViewById(R.id.wealthView);
        actual_wealth_per_second = (TextView) findViewById(R.id.wps_view);


        updateInfo();
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(), "Updating...", Toast.LENGTH_SHORT).show();
                updateInfo();
            }
        });

        buttonFastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonFastFood.setText("Fast Food Cost: " + fastFoodCost);
                if(wealth >= fastFoodCost) {
                    fastFoodEmployees++;
                    wealth -= fastFoodCost;
                    fastFoodCost += fastFoodCost * 2;
                    Toast.makeText(getApplicationContext(), "Let's flip some burgers!", Toast.LENGTH_SHORT).show();
                }
                updateInfo();
            }
        });

        buttonWareHouse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                buttonWareHouse.setText("Ware House Cost: " + wareHouseCost);
                if(wealth >= wareHouseCost){
                    wareHouseEmployees++;
                    wealth -= wareHouseCost;
                    wareHouseCost += wareHouseCost * 2.5;
                    Toast.makeText(getApplicationContext(), "Prepare thy backs!", Toast.LENGTH_SHORT).show();
                }
                updateInfo();
            }
        });

        buttonBank.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                buttonBank.setText("Bank Cost: " + bankCost);
                if(wealth >= bankCost){
                    bankEmployees++;
                    wealth -= bankCost;
                    bankCost += bankCost * 3;
                    Toast.makeText(getApplicationContext(), "You can account on us!", Toast.LENGTH_SHORT).show();
                }
                updateInfo();
            }
        });

        buttonProgrammer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                buttonProgrammer.setText("Programmer Cost: " + programmerCost);
                if(wealth >= programmerCost){
                    programmers++;
                    wealth -= programmerCost;
                    programmerCost += programmerCost * 4;
                    Toast.makeText(getApplicationContext(), "System.out.println('Hello World!')", Toast.LENGTH_SHORT).show();
                }
                updateInfo();
            }
        });

        buttonProfitTimes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                buttonProfitTimes.setText("Profit x3 Cost: " + 3000);
                if(wealth >= 3000){
                    multiplier += 3;
                    wealth -= 3000;
                    Toast.makeText(getApplicationContext(), "HYPER MODE ENGAGED", Toast.LENGTH_SHORT).show();
                }
                updateInfo();
            }
        });

        buttonMusic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){

                if(!isPlaying) {
                    player.start();
                    isPlaying = true;
                    buttonMusic.setText("Pause");
                } else {
                    player.pause();
                    isPlaying = false;
                    buttonMusic.setText("Play");
                }
            }
        });

        buttonPrestige.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                wealth = 100;
                fastFoodEmployees = 0;
                fastFoodCost = 10;
                wareHouseEmployees = 0;
                wareHouseCost = 200;
                bankEmployees = 0;
                bankCost = 500;
                programmers = 0;
                programmerCost = 2500;
                multiplier = 1;
                Prestige++;
                Toast.makeText(getApplicationContext(),"Prestige, Sacrifice", Toast.LENGTH_SHORT);
                updateInfo();
            }
        });

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                wealth += (((fastFoodEmployees * 7.25) + (wareHouseEmployees * 14) + (bankEmployees * 25) + (programmers * 50)) * (multiplier + Prestige));
            }
        }, 0, 1000);




    }
    public void buttonClick(View view)
    {

        Runnable runnable = new Runnable() {
            public void run() {

                long endTime = System.currentTimeMillis()
                        + 20*1000;

                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime -
                                    System.currentTimeMillis());
                        } catch (Exception e) {}
                    }
                }
            }
        };
        Thread mythread = new Thread(runnable);
        mythread.start();
    }



    public void updateInfo(){
        DecimalFormat df = new DecimalFormat("#.##");
        /** --------------------- Update Information ---------------------**/

        actualWealth.setText(String.valueOf(wealth));
        actual_wealth_per_second.setText(String.valueOf("\t" + df.format(((fastFoodEmployees * 7.25) + (wareHouseEmployees * 14) + (bankEmployees * 25) + (programmers * 50)) * (multiplier + Prestige))));

        buttonFastFood.setText("Fast Food Cost: " + fastFoodCost);
        actualFFEmployees.setText(String.valueOf(fastFoodEmployees));

        buttonWareHouse.setText("Ware House Cost: " + wareHouseCost);
        actualWHEmployees.setText(String.valueOf(wareHouseEmployees));

        buttonBank.setText("Bank Cost: " + bankCost);
        actualBEmployees.setText(String.valueOf(bankEmployees));

        buttonProgrammer.setText("Programmer Cost: " + programmerCost);
        actualPEmployees.setText(String.valueOf(programmers));

        buttonProfitTimes.setText("Profit x3 Cost: " + 3000);
        /** ------------------------------------------------------------ **/

        if(wealth >= fastFoodCost) {
            buttonFastFood.setBackgroundColor(Color.rgb(255, 172, 76));
        } else {
            buttonFastFood.setBackgroundColor(Color.LTGRAY);
        }
        if(wealth >= wareHouseCost){
            buttonWareHouse.setBackgroundColor(Color.rgb(255, 172, 76));
        } else {
            buttonWareHouse.setBackgroundColor(Color.LTGRAY);
        }
        if(wealth >= bankCost){
            buttonBank.setBackgroundColor(Color.rgb(255, 172, 76));
        } else {
            buttonBank.setBackgroundColor(Color.LTGRAY);
        }
        if(wealth >= programmerCost){
            buttonProgrammer.setBackgroundColor(Color.rgb(255, 172, 76));
        } else {
            buttonProgrammer.setBackgroundColor(Color.LTGRAY);
        }
        if(wealth >= 3000){
            buttonProfitTimes.setBackgroundColor(Color.rgb(255, 172, 76));
        } else {
            buttonProfitTimes.setBackgroundColor(Color.LTGRAY);
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_employee_venture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
