package com.simpl;

import com.simpl.Output.OutputGenerator;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command == null || command.isEmpty())
                return;
            OutputGenerator.generate(command);
        }

    }
}
