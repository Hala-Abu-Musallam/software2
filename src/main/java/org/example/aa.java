package org.example;

public class aa {


    public static void main(String[] args) {
        sortEventsAndWriteToFile("eventList.txt", "sortedEvents.txt");
    }

    public static void sortEventsAndWriteToFile(String inputFile, String outputFile) {
        ArrayList<Event> events = new ArrayList<>();

        // Read data from the file and populate the ArrayList
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String username = data[0];
                int date = Integer.parseInt(data[1]);
                int time = Integer.parseInt(data[2]);
                double price = Double.parseDouble(data[3]);
                Event event = new Event(username, date, time, price);
                events.add(event);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort events based on date and time
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event event1, Event event2) {
                int dateComparison = Integer.compare(event1.date, event2.date);
                if (dateComparison != 0) {
                    return dateComparison;
                }
                return Integer.compare(event1.time, event2.time);
            }
        });

        // Write sorted events to a new file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Event event : events) {
                writer.write(event.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Events sorted and written to " + outputFile);
    }
}
