package com.maksatabrayev.bilkeatslocal.main;


import android.os.StrictMode;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    Date currentDate;
    String [] totalMenuArray;
    String menuDateString;

    public Menu(Date date){
        this.currentDate = date;
        this.totalMenuArray = getTotalMenuArray();

    }


    private String [] getTotalMenuArray(){
        String[] line = new String[23];
        int index = 0;


        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL("http://kafemud.bilkent.edu.tr/monu_eng.html");
            //Retrieving the contents of the specified page
            Scanner sc = new Scanner(url.openStream());
            //Instantiating the StringBuffer class to hold the result
            StringBuffer sb = new StringBuffer();
            while(sc.hasNext()) {
                String s = sc.next();
                s+="\n";
                sb.append(s);
            }
            //Retrieving the String from the String Buffer object
            String result = sb.toString();

            //Removing the HTML tags
            result = result.replaceAll("<[^>]*>", "");
            result = result.replaceAll("veya", "");
            result = result.replace(')', ' ');
            result = result.replace('(', ' ');
            result = result.replace(';', ' ');
            result = result.replace("&nbsp", "");
            result = result.replaceAll("k.cal.", "");

            // Getting the menu of the current day
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            menuDateString = dateFormat.format(currentDate);
            if(isSunday(currentDate))
                result = result.substring(result.indexOf(menuDateString, 0), result.indexOf("besin", 0));
            else
                result = result.substring(result.indexOf(menuDateString, 0), result.indexOf(dateFormat.format(getNextDate(currentDate)), 0));

            // Inserting into array
            for(int i = 0; i < line.length; i++){
                if(i< line.length-1){
                    line[i] = result.substring(index, result.indexOf("/" , index + 1));
                    index = result.indexOf("/" , index + 1);
                }
                else{
                    line[i] = result.substring(index);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return line;
    }


    private boolean isSunday(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }


    private Date getNextDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);

        return calendar.getTime();
    }

    public String [] getLunchMeals(){
        String [] lunchMeals = new String[4];
        int mealCount = 0;
        for (int i = 2; i < 8; i++) {

            if(i != 4 && i!=5){
                Scanner in = new Scanner (totalMenuArray[i]);
                String meal= "";
                int stringCount = 0;
                while (in.hasNext()){
                    String s = in.next();
                    if(!s.equals("/")){
                        if((Character.isLowerCase(s.charAt(0)) || stringCount == 0) && stringCount < 3){
                            meal+=" "+s;
                            stringCount ++;
                        }
                    }
                }

                lunchMeals[mealCount] = meal;
                mealCount++;
                in.close();
            }
        }

        return lunchMeals;
    }

    public int getLunchCalorie(){
        Scanner in = new Scanner(totalMenuArray[8]);
        int calorie=0;
        while(in.hasNext()){
            String s = in.next();
            if(s.charAt(0) == '1' || s.charAt(0) == '9'){
                calorie = Integer.parseInt(s);
            }
        }
        in.close();
        return calorie;
    }

    public int getLunchCarbohyrate(){
        Scanner in = new Scanner (totalMenuArray[9]);
        int carbohydrate = 0;
        while(in.hasNext()){
            String s = in.next();
            if(s.charAt(0)== '%'){
                s=s.replace("%", "");
                carbohydrate = Integer.parseInt(s);
            }
        }
        in.close();
        return getLunchCalorie() * carbohydrate/100 ;
    }

    public int getLunchProtein(){
        Scanner in = new Scanner (totalMenuArray[10]);
        int protein = 0;
        while(in.hasNext()){
            String s = in.next();
            if(s.charAt(0)== '%'){
                s=s.replace("%", "");
                protein = Integer.parseInt(s);
            }
        }
        in.close();
        return getLunchCalorie() * protein/100 ;
    }

    public int getLunchFat(){
        Scanner in = new Scanner (totalMenuArray[11]);
        int fat = 0;
        while(in.hasNext()){
            String s = in.next();
            if(s.contains("%")){
                s=s.replace("Fat:%", "");
                fat = Integer.parseInt(s);
            }
        }
        in.close();
        return getLunchCalorie() * fat/100 ;
    }


    public String [] getDinnerMeals(){
        String [] dinnerMeals = new String[4];
        int mealCount = 0;
        for (int i = 13; i <= 18; i++) {

            if(i != 15 && i!= 16){
                Scanner in = new Scanner (totalMenuArray[i]);
                String meal= "";
                int stringCount = 0;
                while (in.hasNext()){
                    String s = in.next();
                    if(!s.equals("/")){
                        if((Character.isLowerCase(s.charAt(0)) || stringCount == 0) && stringCount < 3 ){
                            meal+=" "+s;
                            stringCount ++;
                        }
                    }
                }
                dinnerMeals[mealCount] = meal;
                mealCount++;
                in.close();
            }
        }

        return dinnerMeals;
    }

    public int getDinnerCalorie(){
        Scanner in = new Scanner(totalMenuArray[19]);
        int calorie=0;
        while(in.hasNext()){
            String s = in.next();
            if(s.charAt(0) == '1' || s.charAt(0) == '9'){
                calorie = Integer.parseInt(s);
            }
        }
        in.close();
        return calorie;
    }

    public int getDinnerCarbohydrate(){
        Scanner in = new Scanner (totalMenuArray[20]);
        int carbohydrate = 0;
        while(in.hasNext()){
            String s = in.next();
            if(s.charAt(0)== '%'){
                s=s.replace("%", "");
                carbohydrate = Integer.parseInt(s);
            }
        }
        in.close();
        return getDinnerCalorie() * carbohydrate/100 ;
    }

    public int getDinnerProtein(){
        Scanner in = new Scanner (totalMenuArray[21]);
        int protein = 0;
        while(in.hasNext()){
            String s = in.next();
            if(s.charAt(0)== '%'){
                s=s.replace("%", "");
                protein = Integer.parseInt(s);
            }
        }
        in.close();
        return getDinnerCalorie() * protein/100 ;
    }

    public int getDinnerFat(){
        Scanner in = new Scanner (totalMenuArray[22]);
        int fat = 0;
        while(in.hasNext()){
            String s = in.next();
            if(s.contains("%")){
                s=s.replace("Fat:%", "");
                fat = Integer.parseInt(s);
            }
        }
        in.close();
        return getDinnerCalorie() * fat/100 ;
    }

}
