import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class main {

    Digraph g;
    String[][] weeks;

    ArrayList<String> arr = new ArrayList<>();

    public void reader(String path) throws FileNotFoundException {
//--------------------------------------------------------
// Summary: Reads the input file and creates the edges of the Directed Graphs
// name is given.
// Precondition: the input file is not read and parsed, the graph is not been created.
// Postcondition: input file read and parsed according to our purpose and a Directed Graph been created according to inputs.
//--------------------------------------------------------
        File myObj = new File(path);
        Scanner myReader = new Scanner(myObj); // scanner object
        int graphSize = myReader.nextInt(); // the graph size
        Digraph g = new Digraph(graphSize); //Create a directed graph.
        for (int i = 0; i < graphSize + 1; i++) {
            String data = myReader.nextLine();
            arr.add(data); // reading Strings that represents edges and keep them to later print
        }
        arr.remove(0);
        while (myReader.hasNextLine()) {

            String data = myReader.nextLine();
            String[] edges = data.split(" "); // splits the edges
            if (edges[0].equals("-1")) break; //checks for last edge
            g.addEdge(Integer.parseInt(edges[0]), Integer.parseInt(edges[1])); // Creates edges

        }
        myReader.close();

        this.g = g; //initialize graph
        createWeeks();
    }

    public void createWeeks() {
        //--------------------------------------------------------
// Summary: calls the topological sort and splits the assignments to the weeks
// name is given.
// Precondition: the weeks are not been created
// Postcondition: The weeks are created and ready to use
//--------------------------------------------------------

        topologicalSort sort = new topologicalSort(g); //Creates a topological sort object
        ArrayList<Integer> topOrder = sort.topological(); //calls topological sort
        this.weeks = new String[g.V() / 3 + 1][3]; //creates an array with input values size

        int counter = 0; // keeps track of the index of topOrder arraylist
        for (int i = 0; i < 3; i++) {
            weeks[i][0] = arr.get(topOrder.get(counter)); //takes the first element that has degree 0
            counter++;
        }
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 2; i++) {
                weeks[j][i + 1] = arr.get(topOrder.get(counter)); //last two works to the week
                counter++;
            }
        }


    }

    private void listSchedule() {
//--------------------------------------------------------
// Summary: it prints the schedule with respect to weeks
// name is given.
// Precondition: the schedule is not listed
// Postcondition: The schedule is listed.
//--------------------------------------------------------


        for (int i = 0; i < 1; i++) {
            System.out.print("Week " + (i + 1) + ": "); // prints the number of the weeks
            for (int j = 0; j < 3; j++) {
                if (weeks[i][j] == null) continue;
                System.out.print(weeks[i][j] + " "); // prints the scheduled works in that week.

            }

            System.out.println();
        }
        for (int i = 1; i < g.V() / 3 + 1; i++) {
            System.out.print("Week " + (i + 1) + ": "); // prints the number of the weeks
            for (int j = 0; j < 1; j++) {


                if (weeks[i][0] == null) {
                }

                else{
                    System.out.print(weeks[i][0] + " ");
                }
                System.out.print(weeks[i][2] + " ");
                System.out.print(weeks[i][1] + " ");


            }

            System.out.println();
        }


    }


    public void checkOrder(String first, String second) {

        //--------------------------------------------------------
// Summary: Checks the order of the tasks and return a result respect to their order.
// name is given.
// Precondition: the order of the tasks are not calculated.
// Postcondition: the order of the tasks are calculated.
//--------------------------------------------------------
        String firstOr = "";// to keep the order of the first element
        String secondOr = ""; // to keep the order of the second element
        for (int i = 0; i < g.V() / 3 + 1; i++) {
            for (int j = 0; j < 3; j++) {
                if (weeks[i][j] == null) continue;
                if (weeks[i][j].equals(first)) {
                    firstOr = "" + i; //checks and saves the order of the first element

                }

                if (weeks[i][j].equals(second)) { // checks and saves the order of the second element
                    secondOr = "" + i;

                }


            }

        }
        if (firstOr.isEmpty() || secondOr.isEmpty()) {
            System.out.println("wrong input"); // returns worng inputs
        } else {
            if (Integer.parseInt(firstOr) < Integer.parseInt(secondOr)) { //  prints the order of the tasks
                System.out.println("You should do " + first + " before " + second + ".");
            } else if (Integer.parseInt(firstOr) > Integer.parseInt(secondOr)) {
                System.out.println("You should do " + first + " after " + second + ".");
            } else {
                System.out.println("You should do " + first + " and " + second + " on the same week.");
            }
        }


    }


    public static void main(String[] args) throws FileNotFoundException {
        //--------------------------------------------------------
// Summary: gives input file to the program and creates interaction with the user.
// name is given.
// Precondition: user can not interact with program
// Postcondition: user can interact with the program
//--------------------------------------------------------
        main m = new main();

        m.reader("C:\\Users\\m_722\\IdeaProjects\\hmw3\\src\\sampleinput.txt"); // input file path
        Scanner scan = new Scanner(System.in);
        boolean check = true;
        while (check) { //loop to create interact with program
            System.out.println("Enter choice (0: Exit, 1: List schedule, 2: Check order):");
            int next = scan.nextInt();
            if (next == 0) {
                check = false;

            }
            if (next == 1) {
                m.listSchedule(); //prints list
            }
            if (next == 2) {
                System.out.println("Enter first task: ");
                String first = scan.next();

                System.out.println("Enter second task: ");
                String second = scan.next();

                m.checkOrder(first, second); // cheks order and prints result

            }

        }

    }
}
