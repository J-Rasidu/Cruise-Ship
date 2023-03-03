public class Cabin {
    public Passenger[] passengers = new Passenger[3];

    // addPassenger method:
    public void addPassenger( Cabin[] cabins, int cNumber, String firstName, String sureName, double expenses ) {

        if ( cabins[cNumber - 1].passengers[0].getFirstName().equalsIgnoreCase("-") ) {
            cabins[cNumber - 1].passengers[0].setFirstName(firstName);
            cabins[cNumber - 1].passengers[0].setSureName(sureName);
            cabins[cNumber - 1].passengers[0].setExpenses(expenses);
        }
        else if ( cabins[cNumber - 1].passengers[1].getFirstName().equalsIgnoreCase("-") ) {
            cabins[cNumber - 1].passengers[1].setFirstName(firstName);
            cabins[cNumber - 1].passengers[1].setSureName(sureName);
            cabins[cNumber - 1].passengers[1].setExpenses(expenses);
        }
        else if ( cabins[cNumber - 1].passengers[2].getFirstName().equalsIgnoreCase("-") ) {
            cabins[cNumber - 1].passengers[2].setFirstName(firstName);
            cabins[cNumber - 1].passengers[2].setSureName(sureName);
            cabins[cNumber - 1].passengers[2].setExpenses(expenses);
        }
    }

    // viewCabins method:
    public void viewCabins( Cabin[] cabins ) {
        for ( int x = 0; x < cabins.length; x = x + 1 ) {
            System.out.println("Cabin: "+(x+1));
            System.out.println(cabins[x].passengers[0].getFirstName()+" "+cabins[x].passengers[0].getSureName()+" - $"+cabins[x].passengers[0].getExpenses());
            System.out.println(cabins[x].passengers[1].getFirstName()+" "+cabins[x].passengers[1].getSureName()+" - $"+cabins[x].passengers[1].getExpenses());
            System.out.println(cabins[x].passengers[2].getFirstName()+" "+cabins[x].passengers[2].getSureName()+" - $"+cabins[x].passengers[2].getExpenses());
            System.out.print("\n");
        }
    }

    // displayEmptyCabins method:
    public void displayEmptyCabins( Cabin[] cabins ) {
        for ( int i = 0; i < cabins.length; i = i + 1 ) {
            if ( (cabins[i].passengers[0].getFirstName().equalsIgnoreCase("-")) && (cabins[i].passengers[1].getFirstName().equalsIgnoreCase("-")) && (cabins[i].passengers[2].getFirstName().equalsIgnoreCase("-"))  ) {
                System.out.println("Cabin "+(i+1));
            }
        }
        System.out.print("\n");
    }

    // deletePassenger method:
    public void deletePassenger( Cabin[] cabins, String firstName, String sureName ) {
        for ( int x = 1; x < (cabins.length + 1); x = x + 1 ) {
            for ( int m = 0; m < 3; m = m + 1 ) {
                if ( (cabins[x - 1].passengers[m].getFirstName().equalsIgnoreCase(firstName)) && (cabins[x - 1].passengers[m].getSureName().equalsIgnoreCase(sureName)) ) {
                    cabins[x - 1].passengers[m].setFirstName("-");
                    cabins[x - 1].passengers[m].setSureName("");
                    cabins[x - 1].passengers[m].setExpenses(0);
                }
            }
        }
    }

    // findPassenger method:
    public void findPassenger( Cabin[] cabins, String firstName, String sureName ) {
        for ( int n = 0; n < cabins.length; n = n + 1 ) {
            if ( (cabins[n].passengers[0].getFirstName().equalsIgnoreCase(firstName) &&  cabins[n].passengers[0].getSureName().equalsIgnoreCase(sureName)) || (cabins[n].passengers[1].getFirstName().equalsIgnoreCase(firstName) &&  cabins[n].passengers[1].getSureName().equalsIgnoreCase(sureName)) || (cabins[n].passengers[2].getFirstName().equalsIgnoreCase(firstName) &&  cabins[n].passengers[2].getSureName().equalsIgnoreCase(sureName)) ) {
                System.out.println("Cabin "+(n+1));
            }
        }
    }

    // printExpenses method:
    public void printExpenses( Cabin[] cabins, String firstName, String sureName ) {
        for ( int z = 0; z < cabins.length; z = z + 1 ) {
            if ( cabins[z].passengers[0].getFirstName().equalsIgnoreCase(firstName) && cabins[z].passengers[0].getSureName().equalsIgnoreCase(sureName) ) {
                System.out.println("Expenses: $"+cabins[z].passengers[0].getExpenses());
            }
            else if ( cabins[z].passengers[1].getFirstName().equalsIgnoreCase(firstName) && cabins[z].passengers[1].getSureName().equalsIgnoreCase(sureName) ) {
                System.out.println("Expenses: $"+cabins[z].passengers[1].getExpenses());
            }
            else if ( cabins[z].passengers[2].getFirstName().equalsIgnoreCase(firstName) && cabins[z].passengers[2].getSureName().equalsIgnoreCase(sureName) ) {
                System.out.println("Expenses: $"+cabins[z].passengers[2].getExpenses());
            }
        }
        calculateTotalExpenses(cabins);
    }

    // calculateTotalExpenses method:
    public void calculateTotalExpenses( Cabin[] cabins ) {
        double totalExpenses = 0;
        for ( int x = 0; x < cabins.length; x = x + 1 ) {
            totalExpenses = totalExpenses + cabins[x].passengers[0].getExpenses() + cabins[x].passengers[1].getExpenses() + cabins[x].passengers[2].getExpenses();
        }
        System.out.println("\nTotal expenses: $"+totalExpenses);
    }
}
