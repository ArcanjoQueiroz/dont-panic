class Node { 
  constructor(id, name, left = null, right = null) {
    this.id = id;
    this.name = name;
    this.left = left;
    this.right = right;
  }
}

class Tree {
  constructor(nodes, edges) {
      this.root = this._create(nodes, edges);
  }

  _create(nodes, edges) {
    const list = nodes.map(n => new Node(n[0], n[1]));
    const map = {};
    list.forEach(f => map[f.id] = f)
    edges.forEach(e => {
      let node = map[e[0]];
      node.left = map[e[1]];
      node.right = map[e[2]];
    });
    return list[0];
  }

  visit(visitor) {
    this._visit(this.root, visitor);
  }

  _visit(node, fn) {
    if (node) {
      fn(node);
      this._visit(node.left, fn);
      this._visit(node.right, fn);
    }
  }

  find(fn) {
    return this._find(this.root, fn);
  }

  _find (node, fn) {
    return (node) ? 
        fn(node) ? 
          node : 
          this._find(node.left, fn) || this._find(node.right, fn) 
          : null;
  }
  
}

const nodes = [
  [1, 'Node 1'],
  [2, 'Node 2'],
  [3, 'Node 3'],
  [4, 'Node 4'],
  [5, 'Node 5'],
  [6, 'Node 6'],
  [7, 'Node 7'],
  [8, 'Node 8'],
  [9, 'Node 9'],
  [10, 'Node 10'],
  [11, 'Node 11'],
  [12, 'Node 12'],
];

const edges = [
  [ 1,  2,    3 ],
  [ 2,  4       ],
  [ 4,  7,    8 ],
  [ 3,  5,    6 ],
  [ 5,  9,   10 ],
  [ 6, 11,   12 ]
];

// My Tree
let g = new Tree(nodes, edges);

// Visitor Design Pattern
g.visit(n => console.log(n.name));

// Finding accordting to the condition
console.log(g.find(n => n.name == 'Node 5'));