/*
   Zombit infection
================

Dr. Boolean continues to perform diabolical studies on your fellow rabbit kin, and not all of it is taking place in the lab. Reports say the mad doctor has his eye on infecting a rabbit in a local village with a virus that transforms rabbits into zombits (zombie-rabbits)!

Professor Boolean is confident in the virus's ability to spread, and he will only infect a single rabbit. Unfortunately, you and your fellow resistance agents have no idea which rabbit will be targeted. You've been asked to predict how the infection would spread if uncontained, so you decide to create a simulation experiment. In this simulation, the rabbit that Dr. Boolean will initially infect will be called "Patient Z".

So far, the lab experts have discovered that all rabbits contain a property they call "Resistance", which is capable of fighting against the infection. The virus has a particular "Strength" which Dr. Boolean needs to make at least as large as the rabbits' Resistance for it to infect them. 

You will be provided with the following information:
population = A 2D non-empty array of positive integers of the form population[y][x], that is, row then column. (The dimensions of the array are not necessarily equal.) Each cell contains one rabbit, and the value of the cell represents that rabbit's Resistance.
x = The X-Coordinate (column) of "Patient Z" in the population array.
y = The Y-Coordinate (row) of "Patient Z" in the population array.
strength = A constant integer value representing the Strength of the virus.

Here are the rules of the simulation: First, the virus will attempt to infect Patient Z. Patient Z will only be infected if the infection's Strength equals or exceeds Patient Z's Resistance. From then on, any infected rabbits will attempt to infect any uninfected neighbors (cells that are directly - not diagonally - adjacent in the array). They will succeed in infecting any neighbors with a Resistance lower than or equal to the infection's Strength. This will continue until no further infections are possible (i.e., every uninfected rabbit adjacent to an infected rabbit has a Resistance greater than the infection's Strength.)

You will write a function answer(population, x, y, strength), which outputs a copy of the input array representing the state of the population at the end of the simulation, in which any infected cells value has been replaced with -1.
The Strength and Resistance values will be between 0 and 10000. The population grid will be at least 1x1 and no larger than 25x25. The x and y values will be valid indices in the population arrays, with numbering beginning from 0.

Languages
=========

To provide a Python solution, edit solution.py
To provide a Java solution, edit solution.java

Test cases
==========

Inputs:
    (int) population = [[1, 2, 3], [2, 3, 4], [3, 2, 1]]
    (int) x = 0
    (int) y = 0
    (int) strength = 2
Output:
    (int) [[-1, -1, 3], [-1, 3, 4], [3, 2, 1]]

Inputs:
    (int) population = [[6, 7, 2, 7, 6], [6, 3, 1, 4, 7], [0, 2, 4, 1, 10], [8, 1, 1, 4, 9], [8, 7, 4, 9, 9]]
    (int) x = 2
    (int) y = 1
    (int) strength = 5
Output:
    (int) [[6, 7, -1, 7, 6], [6, -1, -1, -1, 7], [-1, -1, -1, -1, 10], [8, -1, -1, -1, 9], [8, 7, -1, 9, 9]]
*/
import java.util.Random;

public class GoogleChallenge2
{
 
    static int[] infectedQueueX;
    static int[] infectedQueueY;
    static int backIndex;
    static int size;
    
    public static void main(String []args)
    {        
        int[][] result;
        int[][] population   = { {2,6,4,7,4,8,6},
                                 {1,3,2,4,5,7,1,1}, 
                                 {9,1,7,4,1,1,1,5},
                                 {2,1,2,3,1,5},
                                 {6,1,1,3,1,2,2},
                                 {2,2,3,4,1,7,4,6,4} };
        int x = 0;
        int y = 0;
        int strength = 3;
        Random r = new Random();
        
        while(true)
        {
            population = makeRandomPopulation(population);
            x = r.nextInt(population[0].length); 
            y = r.nextInt(population.length);
            strength =  r.nextInt(100);
         
            
            System.out.println("RABBIT INFECTED WITH VIRUS!!!");
            System.out.println("Rabbit Coordinate: (" + y +","+ x + ") ");
            System.out.println("Virus Strength   : " + strength);
            
            try{
            displayPopulation(population);
            Delay d =new Delay();
            d.pause(3000);
            }catch(Exception e)
            {
                System.out.println("Timer fucked up...");
            }

            
            result = answer(population, x,y,strength);
            displayPopulation(result);
         
            try{
            Delay d =new Delay();
            d.pause(5000);
            }catch(Exception e)
            {
                System.out.println("Timer fucked up...");
            }
        }
        
    }
  
    public static int[][] answer(int[][] population, int x, int y, int strength) 
    { 
        String coor;
        int xCoor;
        int yCoor;
        
        System.out.println("number of rabbits: " + setQueueSize(population));
        
        if(population[y][x] > strength)
        {
            System.out.println("First Rabbit was not Affected by VIRUS...");
            return population;
        }
        else
        {
            population[y][x] = -1;  // TESTING ********
            pushToInfectedQ(y,x); // First Infected Rabbit
        }
        
        //System.out.println("Infected: " + population[yCoor][xCoor] + "xCoor" + xCoor + "yCoor" + yCoor);
        
        while(!isEmpty())
        {
            displayPopulation(population);
            coor = popOffInfectedQ();        
            xCoor = getY(coor);
            yCoor = getX(coor);
            
            System.out.println(/*"Infected: " + population[yCoor][xCoor] +*/ " (" + (yCoor) + "," + (xCoor) + ")");
            //population[yCoor][xCoor] = -1;
            
        /////////////////////////////////////////////////////////////////////////////////////////
            
            if( yCoor-1 >= 0 )                          // <----Attempting to infect Above
            {
                if ( population[yCoor-1].length > xCoor)
                {
                    System.out.print("   Up: " + population[yCoor-1][xCoor]);
                    
                    if(population[yCoor-1][xCoor] <= strength && population[yCoor-1][xCoor] != -1)
                    {
                        population[yCoor-1][xCoor] = -1;  // TESTING ********
                        pushToInfectedQ(yCoor-1, xCoor);
                        System.out.println("\tINFECTED");
                    }
                    else
                        System.out.println("\tNOT INFECTED");
                }
            }
            
        /////////////////////////////////////////////////////////////////////////////////////////
            
            if( yCoor+1 < population.length )    // <----Attempting to infect Below
            {
                if ( population[yCoor+1].length-1 >= xCoor )
                {
                    System.out.print(" Down: " + population[yCoor+1][xCoor]);
                    
                    if(population[yCoor+1][xCoor] <= strength && population[yCoor+1][xCoor] != -1)
                    {
                        population[yCoor+1][xCoor] = -1;  // TESTING ********
                        pushToInfectedQ(yCoor+1, xCoor);
                        System.out.println("\tINFECTED");
                    }
                    else
                        System.out.println("\tNOT INFECTED");                
                }
            }    
            
        ///////////////////////////////////////////////////////////////////////////////////////// ()
            
            if( xCoor-1 >= 0 )                          // <----Attempting to infect left side
            {
                System.out.print(" Left: " + population[yCoor][xCoor-1]);
                if(population[yCoor][xCoor-1] <= strength && population[yCoor][xCoor-1] != -1)
                {
                    population[yCoor][xCoor-1] = -1;  // TESTING ********
                    pushToInfectedQ(yCoor, xCoor-1);
                    System.out.println("\tINFECTED");
                }
                else
                    System.out.println("\tNOT INFECTED");
            }       
            
        /////////////////////////////////////////////////////////////////////////////////////////
                // 3              
            if( xCoor+1 < population[yCoor].length )           // <----Attempting to infect right side
            {
                System.out.print("Right: " + population[yCoor][xCoor+1]);
                if(population[yCoor][xCoor+1] <= strength && population[yCoor][xCoor+1] != -1)
                {
                    population[yCoor][xCoor+1] = -1;  // TESTING ********
                    pushToInfectedQ(yCoor, xCoor+1);
                    System.out.println("\tINFECTED");
                }
                else
                    System.out.println("\tNOT INFECTED");
            }
            
        /////////////////////////////////////////////////////////////////////////////////////////
            showQ();
            System.out.println("\n");
            /*
            try{
            Delay d =new Delay();
            d.pause(3000);
            }catch(Exception e)
            {
                System.out.println("Timer fucked up...");
            }
            */

        }
     
        System.out.println("Queue is empty");
        return population;
    } 
   
    
    public static void displayPopulation(int [][] pop)
    {
        System.out.print(" ");
        for(int i=0; i<pop.length*5; i++)
        {
            System.out.print("-");
        }
        System.out.print("--\n|");
      
        for(int i=0; i<pop.length; i++)
        {
            for(int j=0; j<pop[i].length; j++)
            {
                if(j==0)
                    System.out.print(" " + pop[i][j]);
                else
                    System.out.print("\t" +pop[i][j]);
            }
            System.out.print("\n|\n|");
        }
        System.out.println();
    }  
    
    public static int setQueueSize(int[][] arr)
    {
        size = 0;
        
        for(int i=0; i<arr.length; i++)
        {
            for(int j=0; j<arr[i].length; j++)
            {   
                size++;
            }
        }
        //size = size*size;
        infectedQueueX = new int[size+1];
        infectedQueueY = new int[size+1];
        backIndex = -1;
        return size;
    }
    
    public static void pushToInfectedQ(int y, int x)
    {
        infectedQueueX[++backIndex] = x;
        infectedQueueY[backIndex] = y;
    }
    
    public static String popOffInfectedQ()
    {
        int tempX;
        int tempY;
        
        tempX = infectedQueueX[0];
        tempY = infectedQueueY[0];
        
        // Shift Elements Left
        for(int i=0; i<infectedQueueX.length-1; i++) // change to back of queue (Not shift entire array)
        {
            infectedQueueX[i] = infectedQueueX[i+1];
            infectedQueueY[i] = infectedQueueY[i+1];
        }
        backIndex--;
        // (Y,X)
        return ""+tempY + "," + tempX;
    }
    
    public static void showQ()
    {
        System.out.println("<---<---<---<---<---<---<---<---"+
                           "<---<---<---<---<---<---<---<---");
        for(int i=0; i<infectedQueueX.length; i++)
        {
            System.out.print("  " + infectedQueueY[i] + "," + infectedQueueX[i] + "");    
        }
        System.out.println();
        System.out.println("<---<---<---<---<---<---<---<---"+
                           "<---<---<---<---<---<---<---<---");
    }
    
    public static boolean isFull()
    {
        return backIndex == size-1;
    } 
    public static boolean isEmpty()
    {
        return backIndex == -1;
    }
    public static int getX(String str)
    {
        StringBuilder sb = new StringBuilder(str);
        return Integer.parseInt(sb.substring(0,sb.indexOf(",")));
    }
    public static int getY(String str)
    {
        StringBuilder sb = new StringBuilder(str);
        return Integer.parseInt(sb.substring(sb.indexOf(",")+1));
    }
    
    public static int[][] makeRandomPopulation(int[][] arr)
    {
        Random r = new Random();
        arr = new int[r.nextInt(10)+1][r.nextInt(10)+1];
        
        for(int i=0; i<arr.length; i++)
            for(int j=0; j<arr[i].length; j++)
                arr[i][j] = r.nextInt(100);
                
        return arr;
    }
   
}

class Delay
{
    public void pause(int time)throws InterruptedException
    {
        synchronized(this){
            wait(time);
        }
    }
}
