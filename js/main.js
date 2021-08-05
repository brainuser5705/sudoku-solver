const puzzle =[[0,0,3,1,2,0,4,0,6],
         [6,0,0,0,0,9,0,2,3],
         [0,8,0,7,3,0,0,5,0],
         [0,0,9,0,0,4,8,1,0],
         [0,0,8,0,0,0,2,0,0],
         [0,6,7,5,0,0,3,0,0],
         [0,9,0,0,5,1,0,7,0],
         [7,3,0,4,0,0,0,0,2],
         [4,0,6,0,9,7,5,0,0]];

var solution = "";

function possible(row, col, num){

  //check row
  for (let c = 0; c < 9; c++){
    if (puzzle[row][c] == num){
      return false;
    }
  }

  //check col
  for (let r = 0; r < 9; r++){
    if (puzzle[r][col] == num){
      return false;
    }
  }

  //check square
  let startRow = Math.floor(row / 3) * 3
  let startCol = Math.floor(col / 3) * 3
  for (let r = startRow; r < startRow + 3; r++){
    for (let c = startCol; c < startCol + 3; c++){
      if (puzzle[r][c] == num){
        return false;
      }
    }
  }

  return true;

}

function solve(){

  for (let r = 0; r < 9; r++){
    for (let c = 0; c < 9; c++){

      if (puzzle[r][c] == 0){

        for (let i = 0; i <= 9; i++){
          if (possible(r,c,i)){
            puzzle[r][c] = i;
            solve();
            puzzle[r][c] = 0;
          }
        }
        return null; //short cuts the loop
      }

    }
  }

  refreshHTML();

}

function toString(){
  let result = "";
  for (let r = 0; r < 9; r++){
    for (let c = 0; c < 9; c++){
      result += puzzle[r][c] + " ";
    }
    result += "<br>";
  }

  return result;
}

function refreshHTML(){
  solution = toString();
  document.getElementById("solution").innerHTML = solution;
}

solve();
