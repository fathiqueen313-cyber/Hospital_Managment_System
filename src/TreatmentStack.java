public class TreatmentStack {
    private StackNode top;

    public TreatmentStack() {
        this.top = null;
    }

    public void push(TreatmentRecord record) {
        if (record == null) {
            System.out.println("Cannot push a null treatment record.");
            return;
        }

        StackNode newNode = new StackNode(record);
        newNode.setNext(top);
        top = newNode;
    }

    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment stack is empty.");
            return null;
        }

        TreatmentRecord record = top.getTreatmentRecord();
        top = top.getNext();
        return record;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty.");
            return;
        }

        System.out.println("\nTreatment History (LIFO order):");
        StackNode current = top;

        while (current != null) {
            System.out.println(current.getTreatmentRecord());
            current = current.getNext();
        }
    }

    public boolean isEmpty() {
        return top == null;
    }
}
