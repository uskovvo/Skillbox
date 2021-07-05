package ru.skillbox;

public class Elevator {
    private int currentFloor = 1;
    private int minFloor;
    private int maxFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        currentFloor = currentFloor > minFloor ? currentFloor - 1 : currentFloor;
    }

    public void moveUp() {
        currentFloor = currentFloor < maxFloor ? currentFloor + 1 : currentFloor;
    }

    public void move(int floor) throws InterruptedException {
        if (floor > getCurrentFloor() && floor <= maxFloor  ) {
            System.out.println("Лифт идет вверх.");
            for(int a = getCurrentFloor(); a < floor; a++){
                if (a == 0) {
                    currentFloor = currentFloor + 1;
                }
                if (getCurrentFloor() == floor){
                    break;
                }
                System.out.println(getCurrentFloor());
                moveUp();
            }
        }else if (floor < getCurrentFloor() && floor >= minFloor){
            System.out.println("Лифт идет вниз.");
            for(int a = getCurrentFloor(); a > floor; a--){
                if (a == 0) {
                    currentFloor = currentFloor -1;
                }
                if (getCurrentFloor() == floor){
                    break;
                }
                System.out.println(getCurrentFloor());
                moveDown();
            }
        }
    }
}
