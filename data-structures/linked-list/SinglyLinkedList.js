class Node {
  constructor(val) {
    this.val = val;
    this.next = null;
  }
}

class SinglyLinkedList {
  constructor() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }

  // add value to the end of the list
  push(val) {
    let newNode = new Node(val);
    if (this.length === 0) {
      this.head = newNode;
      this.tail = newNode;
    } else {
      this.tail.next = newNode;
      this.tail = newNode;
    }
    this.length++;
    return this;
  }

  // removes the last item in the list
  pop() {
    if (!this.head) return undefined;
    let current = this.head;
    let prev = current;
    while (current.next) {
      prev = current;
      current = current.next;
    }
    this.tail = prev;
    this.tail.next = null;
    this.length--;
    // edge case: when last item is removed, set head & tail to null
    // will be refactored later on
    if (this.length === 0) {
      this.head = null;
      this.tail = null;
    }
    return current.val;
  }
}

let ll = new SinglyLinkedList();
ll.push(1);
ll.push(2);
let two = ll.pop();
let one = ll.pop();
console.log(ll);
