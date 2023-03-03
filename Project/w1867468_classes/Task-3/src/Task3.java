import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Task3 {
    public static void main(String[] args) {
        Scanner cabinScan = new Scanner(System.in);

        Cabin[] cabins = new Cabin[12];

        WaitingQueue waitingList = new WaitingQueue(5);

        initialise(cabins, waitingList); // Calling initialise method

        File cabinFile = new File("cabinDetails.txt");

        while (true) {

            // Menu:
            System.out.print("\n");
            System.out.println("< ------ Menu ------ >");
            System.out.print("Please select from the below options;\nA: Add a passenger to cabin\nV: View all cabins\nE: Display empty cabins\nD: Delete passenger from cabin\nF: Find cabin from passenger name\nS: Store program data into file\nL: Load program data from file\nO: View passengers ordered alphabetically by name\nT: Print expenses of a passenger\nEnter the option letter: ");
            String selectedOption = cabinScan.nextLine();
            System.out.print("\n");

            // Add a customer to cabin option:
            if ( selectedOption.equalsIgnoreCase("a") ) {
                System.out.println("Add a customer to cabin [Option Selected]:");

                try {
                    // Adding Passenger to waiting list:
                    int count = 0;
                    for ( int i = 0; i < cabins.length; i = i + 1 ) {
                        if ( (cabins[i].passengers[0].getFirstName().equalsIgnoreCase("-")) && (cabins[i].passengers[1].getFirstName().equalsIgnoreCase("-")) && (cabins[i].passengers[2].getFirstName().equalsIgnoreCase("-"))  ) {
                            count = count + 1;
                        }
                    }
                    if ( count == 0 ) {
                        System.out.println("Ship is already Full! [ Enter into the Waiting List ]\n");

                        System.out.print("First name: ");
                        String firstName = cabinScan.nextLine();

                        System.out.print("Surname: ");
                        String sureName = cabinScan.nextLine();

                        System.out.print("Passenger expenses: ");
                        double expenses = Double.parseDouble( cabinScan.nextLine() );

                        if ( firstName.contains(" ") || sureName.contains(" ") ) {
                            System.out.println("Invalid Name! (Please do not use spaces)");
                        }
                        else {
                            waitingList.enQueue(firstName+" "+sureName+" "+expenses); // Adding passenger to waiting list
                        }
                    }

                    // Adding Passenger:
                    else if ( count != 0 ) {
                        System.out.print("Cabin number: ");
                        int cNumber = Integer.parseInt(cabinScan.nextLine());

                        if ( 0 < cNumber && cNumber <= cabins.length ) {

                            if ( cabins[cNumber - 1].passengers[0].getFirstName().equalsIgnoreCase("-") && cabins[cNumber - 1].passengers[1].getFirstName().equalsIgnoreCase("-") && cabins[cNumber - 1].passengers[2].getFirstName().equalsIgnoreCase("-") ) {
                                for ( int x = 1; x <= 3; x = x + 1 ) {
                                    System.out.print("First name: ");
                                    String firstName = cabinScan.nextLine();

                                    System.out.print("Surname: ");
                                    String sureName = cabinScan.nextLine();

                                    System.out.print("Passenger expenses: ");
                                    String stExpenses = cabinScan.nextLine();

                                    if ( isDouble(stExpenses) == false ) {
                                        x = x - 1;
                                        continue;
                                    }

                                    double expenses = Double.parseDouble( stExpenses );

                                    if ( firstName.contains(" ") || sureName.contains(" ") ) {
                                        System.out.println("\nInvalid Name! (Please do not use spaces)\n");
                                        x = x - 1;
                                        continue;
                                    }
                                    else {
                                        Cabin optionA = new Cabin(waitingList); // Creating Cabin Object optionA
                                        optionA.addPassenger( cabins, cNumber, firstName, sureName, expenses ); // Calling addPassenger method

                                        if ( x != 3 ) {
                                            boolean reLoop = false;
                                            boolean endLoop = false;

                                            while (true) {

                                                System.out.print("\n");
                                                System.out.print("Do you want to add any other passenger to [Cabin: "+cNumber+"] (yes/no)? ");
                                                String choice = cabinScan.nextLine();
                                                System.out.print("\n");

                                                if ( choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y") ) {
                                                    reLoop = true;
                                                    break;
                                                }
                                                else if ( choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n") ) {
                                                    endLoop = true;
                                                    break;
                                                }
                                                else {
                                                    System.out.println("Invalid input!");
                                                    continue;
                                                }
                                            }

                                            if ( reLoop == true ) {
                                                continue;
                                            }
                                            else if ( endLoop == true ) {
                                                break;
                                            }

                                        }
                                    }

                                }
                            }

                            else {
                                System.out.println("Cabin "+cNumber+" has been already occupied!");
                            }
                        }

                        else {
                            System.out.println("Cabin number out of range!");
                        }

                    }
                }
                catch (NumberFormatException ex) {
                    System.out.println("Invalid input! - "+ex);
                }

            }

            // View all cabins option:
            else if ( selectedOption.equalsIgnoreCase("v") ) {
                System.out.println("View all cabins [Option Selected]:");

                Cabin optionV = new Cabin(waitingList); // Creating Cabin Object optionV
                optionV.viewCabins(cabins); // Calling viewCabins method
            }

            // Display empty cabins option:
            else if ( selectedOption.equalsIgnoreCase("e") ) {
                System.out.println("Display empty cabins [Option Selected]:");

                Cabin optionE = new Cabin(waitingList); // Creating Cabin Object optionE
                optionE.displayEmptyCabins(cabins); // Calling displayEmptyCabins method
            }

            // Delete passenger from cabin option:
            else if ( selectedOption.equalsIgnoreCase("d") ) {
                System.out.println("Delete passenger from cabin [Option Selected]:");

                System.out.print("First name: ");
                String firstName = cabinScan.nextLine();

                System.out.print("Surname: ");
                String sureName = cabinScan.nextLine();

                Cabin optionD = new Cabin(waitingList); // Creating Cabin Object optionD
                optionD.deletePassenger(cabins,firstName,sureName); // Calling deletePassenger method

            }

            // Find cabin from passenger name option:
            else if ( selectedOption.equalsIgnoreCase("f") ) {
                System.out.println("Find cabin from passenger name [Option Selected]");

                System.out.print("First name: ");
                String firstName = cabinScan.nextLine();

                System.out.print("Surname: ");
                String sureName = cabinScan.nextLine();

                Cabin optionF = new Cabin(waitingList); // Creating Cabin Object optionF
                optionF.findPassenger(cabins,firstName,sureName); // Calling findPassenger method
            }

            // Store program data into file option:
            else if ( selectedOption.equalsIgnoreCase("s") ) {
                System.out.println("Store program data into file [Option Selected]");

                fileStore(cabins); // Calling fileStore method
            }

            // Load program data from file option:
            else if ( selectedOption.equalsIgnoreCase("l") ) {
                System.out.println("Load program data from file [Option Selected]");

                fileLoad(cabins,cabinFile); // Calling fileLoad method
            }

            // View passengers ordered alphabetically by name option:
            else if ( selectedOption.equalsIgnoreCase("o") ) {
                System.out.println("View passengers ordered alphabetically by name [Option Selected]");

                alphaSort(cabins);
            }

            // Print expenses of a passenger option:
            else if ( selectedOption.equalsIgnoreCase("t") ) {
                System.out.println("Print expenses of a passenger [Option Selected]");

                System.out.print("First name: ");
                String firstName = cabinScan.nextLine();

                System.out.print("Surname: ");
                String sureName = cabinScan.nextLine();

                Cabin optionT = new Cabin(waitingList); // Creating Cabin Object optionT
                optionT.printExpenses(cabins,firstName,sureName); // Calling printExpenses method
            }

            else {
                System.out.println("Invalid Option Selected!!");
            }

        }

    }
    // isDouble method: (Checking the Expense value)
    public static boolean isDouble(String input) {
        try {
            double x = Double.parseDouble(input);
            return true;
        }
        catch (NumberFormatException ex) {
            System.out.println("Expense value is Invalid! - "+ex+"\n");
            return false;
        }
    }

    // initialise method:
    private static void initialise( Cabin[] cabins, WaitingQueue waitingList ) {
        for ( int x = 0; x < cabins.length; x = x + 1 ) {
            cabins[x] = new Cabin(waitingList);

            for ( int y = 0; y < 3; y = y + 1 ) {
                cabins[x].passengers[y] = new Passenger();

                cabins[x].passengers[y].setFirstName("-");
                cabins[x].passengers[y].setSureName("");
                cabins[x].passengers[y].setExpenses(0);
            }
        }
    }

    // fileStore method:
    private static void fileStore( Cabin[] cabins ) {
        try {
            FileWriter cabinWriter = new FileWriter("cabinDetails.txt");
            cabinWriter.write("<------Cruise-Ship-Cabin-Details------>\n");
            cabinWriter.write("\n");

            for ( int x = 0; x < cabins.length; x = x + 1 ) {
                cabinWriter.write(cabins[x].passengers[0].getFirstName()+" "+cabins[x].passengers[0].getSureName()+" : $ "+cabins[x].passengers[0].getExpenses()+" ( Cabin "+(x+1)+" )\n");
                cabinWriter.write(cabins[x].passengers[1].getFirstName()+" "+cabins[x].passengers[1].getSureName()+" : $ "+cabins[x].passengers[1].getExpenses()+" ( Cabin "+(x+1)+" )\n");
                cabinWriter.write(cabins[x].passengers[2].getFirstName()+" "+cabins[x].passengers[2].getSureName()+" : $ "+cabins[x].passengers[2].getExpenses()+" ( Cabin "+(x+1)+" )\n");
                cabinWriter.write("\n");
            }
            cabinWriter.close();
        }
        catch (IOException ex) {
            System.out.println("File Store Error! - "+ex);
        }
    }

    // fileLoad method:
    private static void fileLoad( Cabin[] cabins , File cabinFile ) {
        try {
            Scanner loadScan = new Scanner(cabinFile);
            String fileLine;
            while ( loadScan.hasNext() ) {
                fileLine = loadScan.nextLine();
                System.out.println(fileLine);

                if ( fileLine.contains(" ") ) {
                    String linePartsA[] = fileLine.split(" ");
                    int cNumber = Integer.parseInt(linePartsA[7]);

                    String fileLine1 = fileLine;
                    for ( int m = 0; m < 3; m = m + 1 ) {
                        if ( fileLine1.contains("-") ) {
                            cabins[cNumber - 1].passengers[m].setFirstName("-");
                            cabins[cNumber - 1].passengers[m].setSureName("");
                            cabins[cNumber - 1].passengers[m].setExpenses(0);
                        }
                        else if ( fileLine1.contains(" ") ) {
                            String linePartsC[] = fileLine1.split(" ");
                            cabins[cNumber - 1].passengers[m].setFirstName(linePartsC[0]);
                            cabins[cNumber - 1].passengers[m].setSureName(linePartsC[1]);
                            cabins[cNumber - 1].passengers[m].setExpenses(Double.parseDouble(linePartsC[4]));
                        }
                        fileLine1 = loadScan.nextLine();
                        System.out.println(fileLine1);
                    }
                }
            }
            loadScan.close();
        }
        catch (IOException ex) {
            System.out.println("File Load Error! - "+ex);
        }
    }

    // alphaSort method:
    public static void alphaSort(Cabin[] cabins) {
        String[]sortNames = new String[36]; // sortNames array creation

        // Initialising the array with Strings:
        for ( int j = 0; j < sortNames.length; j = j + 1 ) {
            sortNames[j] = "Empty";
        }

        // Adding Full Names, as elements to the array:
        int count = 0;
        for ( int x = 0; x < cabins.length; x = x + 1 ) {
            for ( int y = 0; y < 3; y = y + 1 ) {
                if ( (cabins[x].passengers[y].getFirstName().equalsIgnoreCase("-")) && (cabins[x].passengers[y].getSureName().equalsIgnoreCase("")) ) {
                    continue;
                }
                else {
                    String fullName = cabins[x].passengers[y].getFirstName()+" "+cabins[x].passengers[y].getSureName();
                    sortNames[count] = fullName;
                    count = count + 1;
                }
            }
        }

        // Sorting of the alphabetical order:
        for ( int i = 0; i < sortNames.length - 1; i = i + 1 ) {
            int index = i;
            for ( int j = i + 1; j < sortNames.length; j = j + 1 ) {
                if ( sortNames[index].compareToIgnoreCase(sortNames[j]) > 0 ) {
                    index = j;
                }
                else {
                    continue;
                }
            }
            String value = sortNames[index];
            sortNames[index] = sortNames[i];
            sortNames[i] = value;
        }

        // Printing out the sorted array elements:
        System.out.println("Passengers:");
        for ( int x = 0; x < sortNames.length; x = x + 1 ) {
            if ( sortNames[x].equals("Empty") ) {
                continue;
            }
            else {
                System.out.println( sortNames[x] );
            }
        }
    }
}
