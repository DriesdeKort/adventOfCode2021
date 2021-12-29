console.time('puzzle5');
import * as fs from 'fs';
import path from "path";


//based on code from https://github.com/stevenvdp1/adventofcode_2021/blob/main/day5/day5.js
let fileName: string = path.join(__dirname, 'input.txt');
const data = fs.readFileSync(fileName, 'utf8').split('\n').map(line => line.split(' -> ').map(x=>x.split(",").map(Number)));
// console.log(data);

function getDiagramNoDiagonal(data: number[][][]): number[][] {
    const maxX: number = Math.max(...data.map(line => Math.max(...line[0])));
    const maxY: number = Math.max(...data.map(line => Math.max(...line[1])));
    let diagram: number[][] = new Array(maxX + 1).fill(0).map(() => new Array(maxY + 1).fill(0));
    data.forEach(line => {
        let [[x1, y1], [x2, y2]] = line;
        if (x1 === x2) {
            const start: number = Math.min(y1, y2);
            const end: number = Math.max(y1, y2);
            for (let i = start; i <= end; i++) {
                diagram[x1][i]++;
            }
        } else if (y1 === y2) {
            const start: number = Math.min(x1, x2);
            const end: number = Math.max(x1, x2);
            for (let i = start; i <= end; i++) {
                diagram[i][y1]++;
            }
        }
    });
    
    return diagram;
    

}
console.log("Puzzle 5 part1: " + getDiagramNoDiagonal(data).map(line => line.filter(x => x > 1).length).reduce((sum, val) => sum + val));
console.timeEnd('puzzle5');


