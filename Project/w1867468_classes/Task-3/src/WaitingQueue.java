public class WaitingQueue {
    // Waiting list ( Circular Queue )

    private int front;
    private int rear;
    private String[] waitingList;

    public WaitingQueue( int size ) {
        this.front = -1;
        this.rear = -1;
        this.waitingList = new String[size];
    }

    // enQueue method:
    public void enQueue( String data ) {
        if ( waitingList[ (rear + 1) % waitingList.length ] == null ) {
            if ( front == -1 ) {
                front = front + 1;
                rear = (rear + 1) % waitingList.length;
                waitingList[rear] = data;
            }
            else {
                rear = (rear + 1) % waitingList.length;
                waitingList[rear] = data;
            }
        }
        else {
            System.out.println("Queue is full!");
        }

    }

    // deQueue method:
    public String deQueue() {
        if ( front == -1 ) {
            System.out.println("No Passengers to assign!");
            return null;
        }
        String enterShip = waitingList[front];
        if ( front == rear ) {
            waitingList[front] = null;
            front = (front + 1) % waitingList.length;
            rear = (rear + 1) % waitingList.length;
            return enterShip;
        }
        else {
            waitingList[front] = null;
            front = (front + 1) % waitingList.length;
            return enterShip;
        }
    }
}
