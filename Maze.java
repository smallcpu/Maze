import java.util.Scanner;
 //Jorell Socorro
 public class Maze 
 {
  static Scanner sc = new Scanner(System.in);
  // maze movements
  static char myMove = '\0';
  // cell position
  static int currentCell = 0;
  //help remember the last cell you were in
  static int lastCell = 0;
  //surrounding cells
  static int s1,s2,s3,s4 = 0;
  static int score = 14, moves = 0;
  static boolean advance = true;
  static boolean checkThis = false;
  
  public static void main(String args[]) {
	   // the local variables declared and initialized
	   char answer = 'Y';
	   displayMenu();       
	   while(answer == 'Y' || answer == 'y') 
	   {
		//System.out.println("Advance:"+advance);
		//fixed a bug, whenever the user would have to try again after being stopped
		//advance variable would always stay false, now whenever user want to try again
		//advance will reset to true
		advance = true;
		displayMovement();
		makeYourMove();
		checkThis = checkYourMove();
		mazeStatus();
	      
		System.out.println("move again(Y or N)?");
	    answer = sc.next().charAt(0);
	    moves++;  
	    score--;
	  }
	   System.out.println("***************************");
  }// end main() method

	static void displayMenu() {
	   System.out.println("");
	   System.out.println("***************************");
	   System.out.println("----The Maze Strategy---");
	   System.out.println("");
	}// end method

	static void displayMovement() {
		if(currentCell == 0)
		{
			System.out.println("You have entered the maze!!");
			System.out.println("There is no turning back!!");
			currentCell = 1;
			mazeStatus();
			advance = true;
		}
	      System.out.println("make your move (W, A, S, D)");
	      System.out.println("W = up, A = left, S = down, D = right)");
	}// end method

	static void makeYourMove() {
		myMove = sc.next().charAt(0);
		
		switch(myMove)
		{
		  case 'W': { MoveUp(); break; }
		  case 'A': { MoveLeft(); break; }
		  case 'S': { MoveDown(); break; }
		  case 'D': { MoveRight(); break; }
		}
	   // end program menu
	 }// end method

	//Each cell there is a restriction EXCEPT zone 5
	/*zone1-W cannot and A no return, move right
	  zone2-D cannot and A no return, move down
	  zone5-A S cannot, W no return,move right
	  zone6-W D cannot, A no return,move down
	  zone9-A D,W no return,move down
	  
	  s1,s2,s3,s4 - the surrounding cell variables
	*/
	static boolean checkYourMove() {
		//you are in zone 1 ****
		if(currentCell == 1 && advance == true) {
			
			if (myMove == 'W') {
				advance = false;
				System.out.println("No zone, try again");
				return advance;
			}
			if (myMove == 'A') {
				advance = false;
				System.out.println("YOU MUST VENTURE FORTH");
				return advance;
			}
			//cell 2
			if (myMove == 'D') {
				lastCell = currentCell;
				currentCell = 2;
				advance = true;
				System.out.println("continue through the maze");
				//System.out.println("the last cell was:"+lastCell);
				return advance;
			}
			//cell 4
			if (myMove == 'S') {
				lastCell = currentCell;
				currentCell = 4;
				advance = true;
				System.out.println("continue through the maze");
				return advance;
			}
		}
		//you are in zone 2 ****
		if(currentCell == 2 && advance == true) {
			//surrounding zones clockwise
			s2 = 3;
			s3 = 5;
			s4 = 1;
			//ZONE NULL
			if (myMove == 'W') {
				advance = false;
				System.out.println("No zone, try again");
				return advance;
			}
			//ZONE 1
			if (myMove == 'A') {
				if(lastCell == s4) {
					advance = false;
					System.out.println("cannot go back to zone");
				}
				else {
					System.out.println("lastcell:"+ lastCell);
					advance = true;
					currentCell = 1;
					System.out.println("continue through the maze");
					return advance;
				}
			}
			//zone 3
			if (myMove == 'D') {		
				if(lastCell == s2) {
					advance = false;
					System.out.println("cannot go back to zone");
				}
				else {
					lastCell = currentCell;
					advance = true;
					currentCell = 3;
					System.out.println("continue through the maze");
					return advance;
				}
			}
			//zone 5
			if (myMove == 'S') {			
				if(lastCell == s3) {
					advance = false;
					System.out.println("cannot go back to zone");
				}
				else {
					lastCell = currentCell;
					System.out.println("lastcell:"+ lastCell);
					currentCell = 5;
					advance = true;
					System.out.println("continue through the maze");
					return advance;
				}
			}
		}
		
		
		//YOU ARE IN ZONE 3 ****
		if(currentCell == 3 && advance == true) {
			//surrounding zones clockwise
			//s1 = 2;
			//s2 = 6;
			s3 = 6;
			s4 = 2;
			//zone NULL
			if (myMove == 'W') {
					advance = false;
					System.out.println("NO zone");			
			}
			//zone NULL
			if (myMove == 'D') {
				advance = false;
				System.out.println("NO zone");	
			}
			//zone 6
			if (myMove == 'S') {
				if(lastCell == s3) {
					advance = false;
					System.out.println("cannot go back to previous zone");
				}
				else {
					lastCell = currentCell;
					System.out.println("Last Zone:"+lastCell);
					advance = true;
					currentCell = 6;
					System.out.println("continue through the maze");
					return advance;
				}
			}
			//zone 2
			if (myMove == 'A') {
				if(lastCell == s4) {
					advance = false;
					System.out.println("cannot go back to previous zone");
				}
				else {
					lastCell = currentCell;
					System.out.println("Last Zone:"+lastCell);
					advance = true;
					currentCell = 2;
					System.out.println("continue through the maze");
					return advance;
				}
			}
		}
		
		
		//YOU ARE IN ZONE 4 ****
		if(currentCell == 4 && advance == true) {
			//surrounding zones clockwise
			s1 = 1;
			s2 = 5;
			s3 = 7;
			//s4 = 5;
			
			//zone 1
			if (myMove == 'W') {
				if(lastCell == s1) {
					advance = false;
					System.out.println("cannot go back to previous zone");
				}
				else {
					lastCell = currentCell;
					System.out.println("Last Zone:"+lastCell);
					advance = true;
					currentCell = 1;
					System.out.println("continue through the maze");
					return advance;
				}
			}
			//zone 5
			if (myMove == 'D') {
				if(lastCell == s2) {
					advance = false;
					System.out.println("cannot go back to previous zone");
				}
				else {
					lastCell = currentCell;
					System.out.println("Last Zone:"+lastCell);
					advance = true;
					currentCell = 5;
					System.out.println("continue through the maze");
					return advance;
				}	
			}
			//zone 7
			if (myMove == 'S') {
				if(lastCell == s3) {
					advance = false;
					System.out.println("cannot go back to previous zone");
				}
				else {
					lastCell = currentCell;
					System.out.println("Last Zone:"+lastCell);
					advance = true;
					currentCell = 7;
					System.out.println("continue through the maze");
					return advance;
				}
			}
			//zone NULL
			if (myMove == 'A') {
					advance = false;
					System.out.println("NO ZONE, TRY AGAIN");				
			}
		}
		
		//YOU ARE IN ZONE 5 ****
				if(currentCell == 5 && advance == true) {
					//surrounding zones clockwise
					s1 = 2;
					s2 = 6;
					s3 = 8;
					s4 = 4;
					//zone 2
					if (myMove == 'W') {
						if(lastCell == s1) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 2;
							System.out.println("continue through the maze");
							return advance;
						}
					}
					//zone 6
					if (myMove == 'D') {
						if(lastCell == s2) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 6;
							System.out.println("continue through the maze");
							return advance;
						}
					}
					//zone 8
					if (myMove == 'S') {
						if(lastCell == s3) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 8;
							System.out.println("continue through the maze");
							return advance;
						}
					}
					//zone 4
					if (myMove == 'A') {
						if(lastCell == s4) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 4;
							System.out.println("continue through the maze");
							return advance;
						}
					}
				}
				
				//YOU ARE IN ZONE 6 ****
				if(currentCell == 6 && advance == true) {
					//surrounding zones clockwise
					s1 = 3;
					//s2 = 6;
					s3 = 9;
					s4 = 5;
					//zone 3
					if (myMove == 'W') {
						if(lastCell == s1) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 3;
							System.out.println("continue through the maze");
							return advance;
						}
					}
					//zone NULL
					if (myMove == 'D') {
							advance = false;
							System.out.println("NO ZONE, TRY AGAIN");		
					}
					//zone 9
					if (myMove == 'S') {
						if(lastCell == s3) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 9;
							System.out.println("continue through the maze");
							System.out.println("The exit is in this zone, choose wisely");
							return advance;
						}
					}
					//zone 5
					if (myMove == 'A') {
						if(lastCell == s4) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 5;
							System.out.println("continue through the maze");
							return advance;
						}
					}
				}
				
				//YOU ARE IN ZONE 7
				if(currentCell == 7 && advance == true) {
					//surrounding zones clockwise
					s1 = 4;
					s2 = 8;
					//s3 = 9;
					//s4 = 5;
					
					//zone 4
					if (myMove == 'W') {
						if(lastCell == s1) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 4;
							System.out.println("continue through the maze");
							return advance;
						}
					}
					//zone 8
					if (myMove == 'D') {
						if(lastCell == s2) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 8;
							System.out.println("continue through the maze");
							return advance;
						}		
					}
					//zone NULL
					if (myMove == 'S') {
						advance = false;
						System.out.println("NO ZONE, TRY AGAIN");	
					}
					//zone NULL
					if (myMove == 'A') {
						advance = false;
						System.out.println("NO ZONE, TRY AGAIN");	
					}
				}
				
				//YOU ARE IN ZONE 8***
				if(currentCell == 8 && advance == true) {
					//surrounding zones clockwise
					s1 = 5;
					s2 = 9;
					//s3 = 9;
					s4 = 7;
					//zone 5
					if (myMove == 'W') {
						if(lastCell == s1) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 5;
							System.out.println("continue through the maze");
							return advance;
						}
					}
					//zone 9
					if (myMove == 'D') {
						if(lastCell == s2) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 9;
							System.out.println("continue through the maze");
							System.out.println("The exit is in this zone, choose wisely");
							return advance;
						}		
					}
					//zone NULL
					if (myMove == 'S') {
						advance = false;
						System.out.println("NO ZONE, TRY AGAIN");	
					}
					//zone 7
					if (myMove == 'A') {
						if(lastCell == s4) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 7;
							System.out.println("continue through the maze");
							return advance;
						}		
					}
				}
				
				
				//YOU ARE IN ZONE 9****
				if(currentCell == 9 && advance == true) {
					//surrounding zones clockwise
					s1 = 6;
					//s2 = 9;
					//s3 = 9;
					s4 = 8;
					//zone 5
					if (myMove == 'W') {
						if(lastCell == s1) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 6;
							System.out.println("continue through the maze");
							return advance;
						}
					}
					//zone NULL
					if (myMove == 'D') {
							advance = false;
							System.out.println("NO ZONE, TRY AGAIN");		
					}
					//FINISH LINE
					if (myMove == 'S') {
						advance = false;
						System.out.println("You made it out of the maze!!!");
						System.out.println("Your move count:"+moves);
						System.out.println("Your best score:"+score);
						System.out.println("***************NOTE: 10 is the best score***********************");
						System.exit(0);
					}
					//zone 8
					if (myMove == 'A') {
						if(lastCell == s4) {
							advance = false;
							System.out.println("cannot go back to previous zone");
						}
						else {
							lastCell = currentCell;
							System.out.println("Last Zone:"+lastCell);
							advance = true;
							currentCell = 8;
							System.out.println("continue through the maze");
							return advance;
						}		
					}
				}
				
		return advance;
		// end program menu
	 }// end method

	static void MoveLeft() {
	   System.out.println("you moved to the left");
	   
	}//end method
	static void MoveRight() {
		 System.out.println("you moved to the right");
		
	}//end method
	static void MoveUp() {
		System.out.println("you moved up (forward)");
		
	}//end method
	static void MoveDown() {
		System.out.println("you moved down (downward)");
		
	}//end method
	static void mazeStatus() {
		System.out.println("current position: Zone " + currentCell);
		}//end method

 }// end class


