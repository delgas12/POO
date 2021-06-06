package com.company;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<String> options ;
    private int op;

    public Menu(String[] options){
        this.options = Arrays.asList(options);
        this.op = 0;
    }

    public void execute(){
        do{
            showMenu();
            this.op = readOption();
        }while(this.op == -1);
    }

    public void showMenu(){
        System.out.println(this.options.get(0));
        for(int i = 1 ; i < this.options.size() ; i++){
            System.out.print(i);
            System.out.print(" - ");
            System.out.println(this.options.get(i));
        }
        System.out.println("0 - Sair");
    }

    private int readOption(){
        int op;
        Scanner is = new Scanner(System.in);
        System.out.print("Opção: ");
        try{
            op = is.nextInt();
        }catch(InputMismatchException e){
            op = -1;
        }
        if(op < 0 || op > this.options.size()){
            System.out.println("ERRO::Opção Invalida!");
        }
        return op;
    }

    public int getOption(){
        return this.op;
    }

}
