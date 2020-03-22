package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String[]> readData(File file){
        ArrayList<String[]> arr = new ArrayList<>();
        String row;
        try {
            BufferedReader fr = new BufferedReader(new FileReader(file));

            while ((row = fr.readLine())!=null){
                String[] rowSplitted  = row.split(",");
                if(rowSplitted[rowSplitted.length -1].equals("Iris-versicolor")){
                    rowSplitted[rowSplitted.length -1] = "1";
                }else{
                    rowSplitted[rowSplitted.length -1] = "0";
                }

                arr.add(rowSplitted);
            }
            fr.close();
            return arr;
        }catch (FileNotFoundException e){
            System.err.println("Data file not found");
            System.exit(-1);
        }catch (IOException e){
            System.err.println("Data file not found");
            System.exit(-1);
        }
        return  null;
    }


    public static void main(String[] args) {

        File fileTrain = new File(args[0]);
        File fileTest = new File(args[1]);
        ArrayList<String[]> dataTrain = readData(fileTrain);
        ArrayList<String[]> dataTest = readData(fileTest);
        double alfa = Double.parseDouble(args[2]);
        double[] wagi = {0.5,0.5,0.5,0.5};
        Perceptron p = new Perceptron(alfa,wagi);
        p.train(dataTrain);
        p.test(dataTest);
        while(true){
            Scanner scanner = new Scanner(System.in);
            String[] inp = scanner.nextLine().split(",");
            double[] doub = new double[inp.length];
            for(int i = 0 ; i< inp.length;i++){
                doub[i] = Double.parseDouble(inp[i]);
            }
            int res = p.calculateValue(doub);
            if(res == 1){
                System.out.println("Iris-versicolor");
            }else{
                System.out.println("Iris-setosa");
            }
        }
    }
}
