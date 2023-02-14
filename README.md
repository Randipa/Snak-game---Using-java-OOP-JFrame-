# Snak-game---Using-java-OOP-JFrame-

The game follows the below logic

Create a board of width & height
Create a List to store the points(x,y) of snake.
Create a direction(LEFT, RIGHT, TOP, BOTTOM) and for each step(screen refresh or repaint()) add a movement to points of x,y based on direction
Based on arrow button press,
Create a new Point(x,y) based on direction, add it to the top of the List.
Remove the last point in the List.(if no food taken)
If the snake eats food,
Increment all the current points of snake in list. (based on Direction)
add one more points to Snake List copying the point of head before increment(top of array) -> (list.add(point)
Else
Just increment all the current points of snake in list. (based on Direction)

![snake 2](https://user-images.githubusercontent.com/96324718/218487495-696402b9-cfd3-4674-b66e-7b0a75da698e.PNG)
![Snake](https://user-images.githubusercontent.com/96324718/218487505-891501df-bca1-4732-96eb-859802910e6d.PNG)
