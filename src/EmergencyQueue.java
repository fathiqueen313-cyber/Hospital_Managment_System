public class EmergencyQueue {
    private QueueNode front;
    private QueueNode rear;

    public EmergencyQueue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(Patient patient) {
        if (patient == null) {
            System.out.println("Cannot enqueue a null patient.");
            return;
        }

        QueueNode newNode = new QueueNode(patient);

        if (isEmpty()) {
            front = newNode;
            rear = newNode;
            return;
        }

        rear.setNext(newNode);
        rear = newNode;
    }

    public Patient dequeue() {
        if (isEmpty()) {
            return null;
        }

        Patient patient = front.getPatient();
        front = front.getNext();

        if (front == null) {
            rear = null;
        }

        return patient;
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return;
        }

        System.out.println("Emergency Queue (FIFO order):");
        QueueNode current = front;
        int position = 1;

        while (current != null) {
            System.out.println(position + ". " + current.getPatient());
            current = current.getNext();
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }
}
