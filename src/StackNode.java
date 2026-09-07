public class StackNode {
    private TreatmentRecord treatmentRecord;
    private StackNode next;

    public StackNode(TreatmentRecord treatmentRecord) {
        this.treatmentRecord = treatmentRecord;
        this.next = null;
    }

    public TreatmentRecord getTreatmentRecord() {
        return treatmentRecord;
    }

    public void setTreatmentRecord(TreatmentRecord treatmentRecord) {
        this.treatmentRecord = treatmentRecord;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}
