
package binaryfiles;

public class ObjectFiles {

    public static void main(String[] args) {

        int chooser = 0, i;
        boolean exit = false, repeat; //Those are for repeating parts of the program
        System.out.println("Hello " + System.getProperty("user.name") + "!");

        do {
            repeat = true;

            System.out.println("");
            System.out.println("*********************************");
            System.out.println("*What do you want to work with: *");
            System.out.println("*         1 - Users             *");
            System.out.println("*         2 - Flights           *");
            System.out.println("*********************************");
            System.out.println("*      3 - Backup saved data    *");
            System.out.println("*     4 - Restore saved data    *");
            System.out.println("*********************************");
            System.out.println("* (Type another number to exit) *");
            System.out.println("*********************************");
            chooser = Read.Int();
            switch (chooser) {
                case 1: //Work with flights
                    do {

                        repeat = true;

                        System.out.println("");
                        System.out.println("***************************************");
                        System.out.println("*You have selected to work with users.*");
                        System.out.println("***************************************");
                        System.out.println("Do you want to:");
                        System.out.println("1 - Create and save a user");
                        System.out.println("2 - Search for a saved user");
                        System.out.println("3 - Show all saved users");
                        System.out.println("4 - Delete a saved user");
                        System.out.println("5 - Go back");
                        System.out.println("6 - Exit");
                        System.out.println("");
                        chooser = Read.Int();

                        switch (chooser) {
                            case 1:
                                OFworks.writeUser();

                                break;

                            case 2:
                                OFworks.searchUser();

                                break;
                            case 3:
                                OFworks.readUser();
                                break;
                            case 4:
                                OFworks.deleteUser();
                                break;
                            case 5:
                                repeat = false;
                                break;
                            case 6:
                                repeat = false;
                                exit = true;
                                break;
                        }

                    } while (repeat == true);
                    break;

                case 2://Work with flights
                    do {
                        repeat = true;

                        System.out.println("");
                        System.out.println("****************************************");
                        System.out.println("*You have selected to work with flights*");
                        System.out.println("****************************************");
                        System.out.println("Do you want to:");
                        System.out.println("1 - Create and save a flight");
                        System.out.println("2 - Search for a saved flight");
                        System.out.println("3 - Show all saved flights");
                        System.out.println("4 - Delete a saved flight");
                        System.out.println("5 - Go back");
                        System.out.println("6 - Exit");
                        System.out.println("");
                        chooser = Read.Int();

                        switch (chooser) {
                            case 1:
                                OFworks.writeFlight();
                                break;
                            case 2:
                                OFworks.searchFlight();
                                break;
                            case 3:
                                OFworks.readFlight();
                                break;
                            case 4:
                                OFworks.deleteFlight();
                                break;
                            case 5:
                                repeat = false;
                                break;
                            case 6:
                                repeat = false;
                                exit = true;
                                break;
                        }
                    } while (repeat == true);
                    break;
                case 3:
                    OFworks.backup();
                    break;
                case 4:
                    OFworks.restore();
                    break;
                default:
                    exit = true;
            }
        } while (exit == false);

    }

}
