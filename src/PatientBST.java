public class PatientBST {
    private PatientNode root;

    public PatientBST() {
        this.root = null;
    }

    public boolean insert(Patient patient) {
        if (patient == null) {
            return false;
        }

        if (root == null) {
            root = new PatientNode(patient);
            return true;
        }

        PatientNode current = root;
        while (true) {
            if (patient.getPatientId() == current.getPatient().getPatientId()) {
                System.out.println("Duplicate Patient ID found. Patient ID " + patient.getPatientId() + " already exists.");
                return false;
            }

            if (patient.getPatientId() < current.getPatient().getPatientId()) {
                if (current.getLeft() == null) {
                    current.setLeft(new PatientNode(patient));
                    return true;
                }
                current = current.getLeft();
            } else {
                if (current.getRight() == null) {
                    current.setRight(new PatientNode(patient));
                    return true;
                }
                current = current.getRight();
            }
        }
    }

    public Patient search(int patientId) {
        PatientNode current = root;

        while (current != null) {
            if (patientId == current.getPatient().getPatientId()) {
                return current.getPatient();
            }

            if (patientId < current.getPatient().getPatientId()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        return null;
    }

    public boolean delete(int patientId) {
        if (root == null) {
            return false;
        }

        return deleteNode(root, null, patientId);
    }

    private boolean deleteNode(PatientNode current, PatientNode parent, int patientId) {
        if (current == null) {
            return false;
        }

        if (patientId < current.getPatient().getPatientId()) {
            return deleteNode(current.getLeft(), current, patientId);
        }

        if (patientId > current.getPatient().getPatientId()) {
            return deleteNode(current.getRight(), current, patientId);
        }

        // Case 1: node has no children
        if (current.getLeft() == null && current.getRight() == null) {
            if (parent == null) {
                root = null;
            } else if (parent.getLeft() == current) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
            return true;
        }

        // Case 2: node has only left child
        if (current.getLeft() != null && current.getRight() == null) {
            if (parent == null) {
                root = current.getLeft();
            } else if (parent.getLeft() == current) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
            return true;
        }

        // Case 3: node has only right child
        if (current.getLeft() == null && current.getRight() != null) {
            if (parent == null) {
                root = current.getRight();
            } else if (parent.getLeft() == current) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
            return true;
        }

        // Case 4: node has two children
        PatientNode successor = findMin(current.getRight());
        current.setPatient(successor.getPatient());
        return deleteNode(current.getRight(), current, successor.getPatient().getPatientId());
    }

    private PatientNode findMin(PatientNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("Patient BST is empty.");
            return;
        }

        System.out.println("\nPatients in ascending Patient ID order:");
        inOrder(root);
        System.out.println();
    }

    private void inOrder(PatientNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.getLeft());
        System.out.println(node.getPatient());
        inOrder(node.getRight());
    }

    public boolean isEmpty() {
        return root == null;
    }

    public PatientNode getRoot() {
        return root;
    }
}
