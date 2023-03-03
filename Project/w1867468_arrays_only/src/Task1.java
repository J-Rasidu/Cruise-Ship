import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Task1 {
    public static void main(String[] args) {
        String[]cabinNames = new String[12];

        initialise(cabinNames); // Calling initialise method

        Scanner cabinScan = new Scanner(System.in);

        File cabinFile = new File("cabinDetails.txt");

        while (true) {

            // Menu:
            System.out.print("\n");
            System.out.println("< ------ Menu ------ >");
            System.out.print("Please select from the below options;\nA: Add a passenger to cabin\nV: View all cabins\nE: Display empty cabins\nD: Delete passenger from cabin\nF: Find cabin from passenger name\nS: Store program data into file\nL: Load program data from file\nO: View passengers ordered alphabetically by name\nEnter the option letter: ");
            String selectedOption = cabinScan.nextLine();
            System.out.print("\n");

            // Add a customer to cabin option:
            if ( selectedOption.equalsIgnoreCase("a") ) {
                System.out.println("Add a customer to cabin [Option Selected]:");

                try {
                    System.out.print("Cabin Number: ");
                    int cNumber = Integer.parseInt(cabinScan.nextLine());

                    if ( 0 < cNumber && cNumber <= cabinNames.length ) {
                        if ( cabinNames[cNumber - 1].equals("Empty") ) {

                            System.out.print("Cabin Name: ");
                            String cName = cabinScan.nextLine();

                            addPassenger(cabinNames,cName,cNumber); // Calling addPassenger method
                        }
                        else {
                            System.out.println("Cabin has been already occupied!");
                        }
                    }
                    else {
                        System.out.println("Cabin number is out of range!");
                    }
                }
                catch (NumberFormatException ex) {
                    System.out.println("Integer required! - "+ex);
                }

            }

            // View all cabins option:
            else if ( selectedOption.equalsIgnoreCase("v") ) {
                System.out.println("View all cabins [Option Selected]:");

                viewCabins(cabinNames); // Calling viewCabins method
            }

            // Display empty cabins option:
            else if ( selectedOption.equalsIgnoreCase("e") ) {
                System.out.println("Display empty cabins [Option Selected]:");

                displayEmptyCabins(cabinNames); // Calling displayEmptyCabins method
            }

            // Delete Passenger from cabin option:
            else if ( selectedOption.equalsIgnoreCase("d") ) {
                System.out.println("Delete passenger from cabin [Option Selected]:");

                System.out.print("Enter the passenger name: ");
                String delPassenger = cabinScan.nextLine();

                deletePassenger(delPassenger,cabinNames); // Calling deletePassenger method
            }

            // Find cabin from Passenger name option:
            else if ( selectedOption.equalsIgnoreCase("f") ) {
                System.out.println("Find cabin from passenger name [Option Selected]");

                System.out.print("Enter the passenger name: ");
                String findPassenger = cabinScan.nextLine();

                findCabin(findPassenger,cabinNames); // Calling findCabin method
            }

            // Store program data into file option:
            else if ( selectedOption.equalsIgnoreCase("s") ) {
                System.out.println("Store program data into file [Option Selected]");

                fileStore(cabinNames); // Calling fileStore method
            }

            // Load program data from file option:
            else if ( selectedOption.equalsIgnoreCase("l") ) {
                System.out.println("Load program data from file [Option Selected]");

                fileLoad(cabinNames, cabinFile); // Calling fileLoad method
            }

            // View passengers ordered alphabetically by name option:
            else if ( selectedOption.equalsIgnoreCase("o") ) {
                System.out.println("View passengers ordered alphabetically by name [Option Selected]");

                alphaSort(cabinNames); // Calling alphaSort method
            }

            else {
                System.out.println("Invalid Option Selected!!");
            }

        }

    }

    // [1] Initialise method:
    public static void initialise( String cabinNames[] ) {
        for ( int x = 0; x < cabinNames.length; x = x + 1 ) {
            cabinNames[x] = "Empty";
        }
    }

    // [2] addPassenger method:
    public static void addPassenger(String[]cabinNames, String cName, int cNumber) {
        cabinNames[cNumber - 1] = cName;
    }

    // [3] viewCabins method:
    public static void viewCabins(String[]cabinNames) {
        for (int x = 0; x < cabinNames.length; x = x + 1) {
            if (cabinNames[x].equals("Empty")) {
                System.out.println("Cabin " + (x + 1) + " is Empty!");
            }
            else {
                System.out.println("Cabin " + (x + 1) + " is occupied by " + cabinNames[x]);
            }
        }
    }

    // [4] displayEmptyCabins method:
    public static void displayEmptyCabins(String[]cabinNames) {
        for (int y = 0; y < cabinNames.length; y = y + 1) {
            if (cabinNames[y].equals("Empty")) {
                System.out.println("Cabin " + (y + 1));
            }
        }
    }

    // [5] deletePassenger method:
    public static void deletePassenger(String delPassenger, String[]cabinNames) {
        for (int z = 0; z < cabinNames.length; z = z + 1) {
            if (cabinNames[z].equalsIgnoreCase(delPassenger)) {
                cabinNames[z] = "Empty";
            }
        }
    }

    // [6] findCabin method:
    public static void findCabin(String findPassenger, String[]cabinNames) {
        for (int a = 0; a < cabinNames.length; a = a + 1) {
            if (cabinNames[a].equalsIgnoreCase(findPassenger)) {
                System.out.println("Cabin No: " + (a + 1));
            }
        }
    }

    // [7] fileStore method:
    public static void fileStore(String[]cabinNames) {
        try {
            FileWriter cabinWriter = new FileWriter("cabinDetails.txt");
            cabinWriter.write("< ----- Cruise Ship Cabin Details ----- >\n");

            for (int b = 0; b < cabinNames.length; b = b + 1) {
                if (cabinNames[b].equals("Empty")) {
                    cabinWriter.write("Cabin " + (b + 1) + " : Empty\n");
                }
                else {
                    cabinWriter.write("Cabin " + (b + 1) + " : " + cabinNames[b]+"\n");
                }
            }

            cabinWriter.close();
        }
        catch (IOException ex) {
            System.out.println("File Store Error!");
        }
    }

    // [8] fileLoad method:
    public static void fileLoad(String[]cabinNames, File cabinFile) {
        try {
            Scanner loadScan = new Scanner(cabinFile);
            String fileLine;
            int m = 0;
            while ( loadScan.hasNext() ) {
                fileLine = loadScan.nextLine();
                System.out.println(fileLine);

                if ( fileLine.contains(" : ") ) {
                    String lineParts[] = fileLine.split(" : ");
                    cabinNames[m] = lineParts[1];
                    m = m + 1;
                }
            }
            loadScan.close();
        }
        catch (IOException ex) {
            System.out.println("File Load Error!");
        }
    }

    // [9] alphaSort method:
    public static void alphaSort(String[] cabinNames) {
        String sortNames[] = cabinNames.clone();

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
