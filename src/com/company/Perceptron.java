package com.company;

import java.util.ArrayList;

public class Perceptron {
    public double prog =0.5;
    public double[] wagi;
    public double alfa;
    public Perceptron(double alfa, double[] wagi){
        this.wagi = wagi;
        this.alfa = alfa;
    }
    public static void printArr(String[] arr){
        for(int i = 0 ; i < arr.length; i++){
            System.out.print(arr[i]);

        }
        System.out.println();
    }
    public static void printArr(double[] arr){
        for(int i = 0 ; i < arr.length; i++){
            System.out.print(arr[i]);

        }
        System.out.println();
    }
    public static double matrix(double[] wagi, double[] numbers){
        double sum = 0;
        for(int i = 0 ; i< numbers.length; i++){
            sum = sum+ wagi[i]*numbers[i];
        }
        return sum;
    }
    public int calculateValue(double[] numbers){
        double res = matrix(this.wagi, numbers);
        //System.out.println(res + "res heer");
        //System.out.println(this.prog + "prog heer");
        if(res >= this.prog){
            return 1;
        }else{
            return 0;
        }

    }
    public void changeWagi(int oczekiwane, int faktyczna, double[] numbers){
        //printArr(this.wagi);
        for(int i = 0 ; i<this.wagi.length; i++){
            this.wagi[i] = this.wagi[i] + (oczekiwane - faktyczna) * alfa * numbers[i];
        }
        this.prog =  this.prog+ (oczekiwane - faktyczna) * alfa * (-1);
       // printArr(this.wagi);
    }

    public void train(ArrayList<String[]> data){
        for (int i = 0; i < data.size(); i++) {


            double[] values = new double[data.get(i).length - 1];
            for (int j = 0; j < values.length; j++) {
                values[j] = Double.parseDouble(data.get(i)[j]);
            }
            int res = calculateValue(values);
            boolean flag = true;
            if (res != Integer.parseInt(data.get(i)[data.get(i).length - 1])) {
                while(flag) {
                    //printArr(this.wagi);
                    //System.out.println("wagi while");
                    changeWagi(Integer.parseInt(data.get(i)[data.get(i).length - 1]), res, values);
                    res = calculateValue(values);
                    if(res == Integer.parseInt(data.get(i)[data.get(i).length - 1])){
                        flag = false;
                    }
                }

            }

        }
    }
    public void test(ArrayList<String[]> testData){
        //System.out.println("++++++++++++++++++TEST++++++++++++++++++++");
        double accuracy = 0;
        double all = 0;
        for(String[] testArr: testData){
            double[] values = new double[testArr.length - 1];
            for(int i = 0 ; i<values.length; i++){
                values[i] = Double.parseDouble(testArr[i]);
            }
            //printArr(testArr);

            int ans = calculateValue(values);
            System.out.println(ans);
            if(ans == Integer.parseInt(testArr[testArr.length -1])){
                accuracy++;
            }
            all++;
        }
        System.out.println("Accuracy of predictions is:" + ((accuracy /all)*100) + "%");
    }
}
