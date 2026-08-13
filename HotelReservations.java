  import java.util.ArrayList;
import java.util.Scanner;

// Room Class
class Room {

    int roomNumber;
    String type;
    double price;
    boolean available;

    Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.available = true;
    }

    void displayRoom() {
        System.out.println(
            "Room No: " + roomNumber +
            " | Type: " + type +
            " | Price: Rs. " + price +
            " | Status: " + (available ? "Available" : "Booked")
        );
    }
}


// Reservation Class
class Reservation {

    String guestName;
    String phone;
    int roomNumber;
    String roomType;
    double price;
    int nights;
    double totalBill;

    Reservation(String guestName, String phone,
                int roomNumber, String roomType,
                double price, int nights) {

        this.guestName = guestName;
        this.phone = phone;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.nights = nights;
        this.totalBill = price * nights;
    }

    void displayReservation() {

        System.out.println("-----------------------------------");
        System.out.println("Guest Name  : " + guestName);
        System.out.println("Phone       : " + phone);
        System.out.println("Room Number : " + roomNumber);
        System.out.println("Room Type   : " + roomType);
        System.out.println("Price/Night : Rs. " + price);
        System.out.println("Nights      : " + nights);
        System.out.println("Total Bill  : Rs. " + totalBill);
        System.out.println("-----------------------------------");
    }
}


// Main Hotel Reservation Class
public class HotelReservations {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();


    public static void main(String[] args) {

        addDefaultRooms();

        while (true) {

            System.out.println("\n======================================");
            System.out.println("       HOTEL RESERVATION SYSTEM");
            System.out.println("======================================");

            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Reservations");
            System.out.println("4. Search Reservation");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");

            System.out.println("======================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine();
            }
            catch (Exception e) {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
                continue;
            }


            switch (choice) {

                case 1:
                    viewRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    viewReservations();
                    break;

                case 4:
                    searchReservation();
                    break;

                case 5:
                    cancelReservation();
                    break;

                case 6:
                    checkout();
                    break;

                case 7:
                    System.out.println("\nThank you for using Hotel Reservation System!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // Add default rooms
    static void addDefaultRooms() {

        rooms.add(new Room(101, "Single", 1500));
        rooms.add(new Room(102, "Single", 1500));

        rooms.add(new Room(201, "Double", 2500));
        rooms.add(new Room(202, "Double", 2500));

        rooms.add(new Room(301, "Deluxe", 4000));
        rooms.add(new Room(302, "Deluxe", 4000));
    }


    // View rooms
    static void viewRooms() {

        System.out.println("\n========== ROOM DETAILS ==========");

        for (Room room : rooms) {

            room.displayRoom();
        }
    }


    // Book a room
    static void bookRoom() {

        System.out.println("\n========== BOOK A ROOM ==========");

        System.out.print("Enter Guest Name: ");
        String name = sc.nextLine();

        if (name.trim().isEmpty()) {

            System.out.println("Name cannot be empty.");
            return;
        }


        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();


        System.out.println("\nAvailable Rooms:");

        boolean roomAvailable = false;

        for (Room room : rooms) {

            if (room.available) {

                room.displayRoom();
                roomAvailable = true;
            }
        }


        if (!roomAvailable) {

            System.out.println("Sorry! No rooms are available.");
            return;
        }


        System.out.print("\nEnter Room Number: ");
        int roomNumber = sc.nextInt();


        Room selectedRoom = null;

        for (Room room : rooms) {

            if (room.roomNumber == roomNumber) {

                selectedRoom = room;
                break;
            }
        }


        if (selectedRoom == null) {

            System.out.println("Room does not exist.");
            return;
        }


        if (!selectedRoom.available) {

            System.out.println("This room is already booked.");
            return;
        }


        System.out.print("Enter Number of Nights: ");
        int nights = sc.nextInt();
        sc.nextLine();


        if (nights <= 0) {

            System.out.println("Number of nights must be greater than 0.");
            return;
        }


        Reservation reservation = new Reservation(
            name,
            phone,
            selectedRoom.roomNumber,
            selectedRoom.type,
            selectedRoom.price,
            nights
        );


        reservations.add(reservation);

        selectedRoom.available = false;


        System.out.println("\nRoom booked successfully!");

        System.out.println("Guest Name : " + name);
        System.out.println("Room No.   : " + roomNumber);
        System.out.println("Total Bill : Rs. " + reservation.totalBill);
    }


    // View reservations
    static void viewReservations() {

        System.out.println("\n========== ALL RESERVATIONS ==========");

        if (reservations.isEmpty()) {

            System.out.println("No reservations found.");
            return;
        }


        for (Reservation reservation : reservations) {

            reservation.displayReservation();
        }
    }


    // Search reservation
    static void searchReservation() {

        System.out.println("\n========== SEARCH RESERVATION ==========");

        System.out.print("Enter Guest Name: ");
        String name = sc.nextLine();

        boolean found = false;


        for (Reservation reservation : reservations) {

            if (reservation.guestName.equalsIgnoreCase(name)) {

                reservation.displayReservation();

                found = true;
            }
        }


        if (!found) {

            System.out.println("No reservation found for " + name);
        }
    }


    // Cancel reservation
    static void cancelReservation() {

        System.out.println("\n========== CANCEL RESERVATION ==========");

        System.out.print("Enter Room Number: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();


        Reservation foundReservation = null;


        for (Reservation reservation : reservations) {

            if (reservation.roomNumber == roomNumber) {

                foundReservation = reservation;
                break;
            }
        }


        if (foundReservation == null) {

            System.out.println("No reservation found for this room.");
            return;
        }


        for (Room room : rooms) {

            if (room.roomNumber == roomNumber) {

                room.available = true;
                break;
            }
        }


        reservations.remove(foundReservation);

        System.out.println("Reservation cancelled successfully!");
    }


    // Checkout
    static void checkout() {

        System.out.println("\n========== CHECKOUT ==========");

        System.out.print("Enter Room Number: ");
        int roomNumber = sc.nextInt();
        sc.nextLine();


        Reservation foundReservation = null;


        for (Reservation reservation : reservations) {

            if (reservation.roomNumber == roomNumber) {

                foundReservation = reservation;
                break;
            }
        }


        if (foundReservation == null) {

            System.out.println("No reservation found for this room.");
            return;
        }


        System.out.println("\n========== FINAL BILL ==========");

        System.out.println("Guest Name  : " + foundReservation.guestName);
        System.out.println("Room Number : " + foundReservation.roomNumber);
        System.out.println("Room Type   : " + foundReservation.roomType);
        System.out.println("Price/Night : Rs. " + foundReservation.price);
        System.out.println("Nights      : " + foundReservation.nights);

        System.out.println("--------------------------------");
        System.out.println("TOTAL BILL  : Rs. " + foundReservation.totalBill);
        System.out.println("--------------------------------");


        // Make room available again
        for (Room room : rooms) {

            if (room.roomNumber == roomNumber) {

                room.available = true;
                break;
            }
        }


        reservations.remove(foundReservation);

        System.out.println("Checkout completed successfully!");
    }
} 
