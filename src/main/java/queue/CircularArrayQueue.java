package queue;

import interfaces.QueueADT;

    class CircularArrayQueue<T> implements QueueADT<T> {
        private static final int DEFAULT_CIRCULAR_ARRAY_SIZE = 100;
        private static final int ARRAY_MULTIPLIER = 2;
        private int front, rear, count;
        private T[] array;

        public CircularArrayQueue() {
            array = (T[]) new Object[DEFAULT_CIRCULAR_ARRAY_SIZE];
            front = -1;
            rear = -1;
            count = 0;
        }

        @Override
        public void enqueue(T element) {
            if (count == array.length) {
                expandCircle();
            }
            rear = (++rear) % array.length;
            count++;
            array[rear] = element;
        }

        @Override
        public T dequeue() {
            front = (++front) % array.length;
            T removedElement = array[front];
            array[front] = null;
            count--;
            return removedElement;
        }

        @Override
        public T first() {
            return array[(++front % array.length)];
        }

        @Override
        public boolean isEmpty() {
            return (array[rear] == null);
        }

        @Override
        public int size() {
            return count;
        }

        private void expandCircle() {
            T[] temporaryArray = array;
            rear = -1;
            front = -1;
            array = (T[]) new Object[array.length * ARRAY_MULTIPLIER];

            for (int i = 0; i < count; i++) {
                rear = (++rear) % array.length;
                array[rear] = array[i];
            }
        }
    }