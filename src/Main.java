import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
         * Calculator to find number of paint buckets required to paint the walls of a room
         * -- Takes in number of walls
         * -- Takes in measurement system being used
         * -- Takes in paint bucket volume choice (1l, 2.5l, 5l, 10l)
         * -- Considers obstructions on the walls with different shapes
         * -- Outputs number of paint buckets required and total cost to buy them
         */

        // Scanner for user input
        Scanner input = new Scanner(System.in);

        // Variables
        double totalObstructionArea = 0.0;
        double litrePaintBucketCoverage = 12; // metres squared
        double gallonPaintBucketCoverage = 400; // square feet
        double totalWallArea = 0.0;
        double numberOfPaintBuckets;
        int wallCounter = 1;
        int obstructionCounter = 1;
        int bucketSizeSelection = 0;
        String nameOfBucketSize = "";
        String[] walls = new String[0];
        String[][] costOfLitrePaintBucket = {{"1 litres", "2.5 litres", "5 litres", "10 litres"}, {"5.99", "7.99", "12.99", "20.99"}};
        String[][] costOfGallonPaintBucket = {{"quarter pint", "half pint", "one pint", "one quart", "half gallon", "one gallon"},
                {"2.99", "4.99", "8.99", "10.99", "12.99", "24.99", "49.99"}};
        boolean inputValid = false;

        // Ask user for number of walls to be painted
        int numberOfWalls = 0;
        while (!inputValid) {
            System.out.println("How many walls need to be painted?");
            try {
                numberOfWalls = Integer.parseInt(input.nextLine());

                if (numberOfWalls < 0) {
                    System.out.println("Please enter a positive integer.");
                } else {
                    walls = new String[numberOfWalls];
                    inputValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("This input is not an integer - please try again.");
            }
        }

        // Ask user for number of coats to be applied
        int numberOfCoats = 0;
        inputValid = false;
        while (!inputValid) {
            System.out.println("How many coats would you like to apply?");
            try {
                numberOfCoats = Integer.parseInt(input.nextLine());
                if (numberOfCoats < 0) {
                    System.out.println("Please enter a positive integer.");
                } else {
                    inputValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("This input is not an integer - please try again.");
            }
        }

        // Ask user for measurement system they're using
        int measurementSystem = 0;
        inputValid = false;
        while (!inputValid) {
            System.out.println("Which measurement system are you using? (metric/imperial)");
            System.out.println("1 - metric");
            System.out.println("2 - imperial");
            try {
                measurementSystem = Integer.parseInt(input.nextLine());
                if (measurementSystem < 0 || measurementSystem > 2) {
                    System.out.println("Please try again - enter either 1 for metric and 2 for imperial.");
                } else {
                    inputValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("This input is not an integer - please try again.");
            }
        }
        System.out.println("You are using the " + (measurementSystem == 1 ? "metric" : "imperial") + " measurement system. Paint buckets will be in " +
                (measurementSystem == 1 ? "litres" : "pints and gallons") + " and wall measurements will be in " +
                (measurementSystem == 2 ? "metres" : "feet") + ".\n");

        if (measurementSystem == 1) {
            // Ask user for size of paint bucket
            inputValid = false;
            while (!inputValid) {
                System.out.println("Choose the paint bucket volume from the following choices:");
                System.out.println("1 - 1 litre");
                System.out.println("2 - 2.5 litres");
                System.out.println("3 - 5 litres");
                System.out.println("4 - 10 litres");
                try {
                    bucketSizeSelection = Integer.parseInt(input.nextLine());
                    nameOfBucketSize = costOfLitrePaintBucket[0][bucketSizeSelection - 1];
                    inputValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("This input is not an integer - please try again.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter a positive integer between 1 and 4 inclusive.");
                }
            }

            switch (bucketSizeSelection) {
                case 1:
                    break;
                case 2:
                    litrePaintBucketCoverage *= 2.5;
                    break;
                case 3:
                    litrePaintBucketCoverage *= 5;
                    break;
                case 4:
                    litrePaintBucketCoverage *= 10;
                    break;
            }
        } else { // imperial
            // Ask user for size of paint bucket
            inputValid = false;
            while (!inputValid) {
                System.out.println("Choose the paint bucket volume from the following choices:");
                System.out.println("1 - quarter pint");
                System.out.println("2 - half pint");
                System.out.println("3 - one pint");
                System.out.println("4 - one quart");
                System.out.println("5 - half gallon");
                System.out.println("6 - one gallon");
                try {
                    bucketSizeSelection = Integer.parseInt(input.nextLine());
                    nameOfBucketSize = costOfGallonPaintBucket[0][bucketSizeSelection - 1];
                    inputValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("This input is not an integer - please try again.");
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please enter a positive integer between 1 and 6 inclusive.");
                }
            }

            switch (bucketSizeSelection) {
                case 1:
                    gallonPaintBucketCoverage /= 8*4;
                    break;
                case 2:
                    gallonPaintBucketCoverage /= 8*2;
                    break;
                case 3:
                    gallonPaintBucketCoverage /= 8;
                    break;
                case 4:
                    gallonPaintBucketCoverage /= 4;
                    break;
                case 5:
                    gallonPaintBucketCoverage /= 2;
                    break;
                case 6:
                    break;
            }
        }

        // do-while loop to execute for each wall to be painted
        do {
            // Ask user for height of wall
            double height = 0.0;
            inputValid = false;
            while (!inputValid) {
                System.out.println("Please enter the height of wall " + wallCounter + ": ");
                try {
                    height = Double.parseDouble(input.nextLine());
                    if (height < 0) {
                        System.out.println("Please enter a positive number.");
                    } else {
                        inputValid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("This input is not a number - please try again.");
                }
            }

            // Ask user for width of wall
            double width = 0.0;
            inputValid = false;
            while (!inputValid) {
                System.out.println("Please enter the width of wall " + wallCounter + ": ");
                try {
                    width = Double.parseDouble(input.nextLine());
                    if (width < 0) {
                        System.out.println("Please enter a positive number.");
                    } else {
                        inputValid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("This input is not a number - please try again.");
                }
            }

            // Ask if they have obstructions on the wall(s)
            String haveObstruction;
            do {
                System.out.println(("Do you have obstructions on the wall(s)? (Y/N)"));
                haveObstruction = input.nextLine();

                if (!haveObstruction.equalsIgnoreCase("n") && !haveObstruction.equalsIgnoreCase("y")) {
                    System.out.println("Please enter either y or n.");
                }
            } while (!haveObstruction.equalsIgnoreCase("n") && !haveObstruction.equalsIgnoreCase("y"));

            if (haveObstruction.equalsIgnoreCase("y")) {
                // Ask how many obstructions there are
                int numberOfObstructions = 0;
                inputValid = false;
                while (!inputValid) {
                    System.out.println("How many obstructions are on the wall?");
                    try {
                        numberOfObstructions = Integer.parseInt(input.nextLine());
                        if (numberOfObstructions < 0) {
                            System.out.println("Please enter a positive number.");
                        } else {
                            inputValid = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("This input is not a number - please try again.");
                    }
                }

                for (int i = 1; i <= numberOfObstructions; i++) {
                    // Ask if it's a square, circle or triangle
                    int whichShape = 0;
                    inputValid = false;
                    while (!inputValid) {
                        System.out.println("Is obstruction number " + obstructionCounter + " a square, circle or triangle?");
                        System.out.println("1 - square");
                        System.out.println("2 - circle");
                        System.out.println("3 - triangle");
                        try {
                            whichShape = Integer.parseInt(input.nextLine());
                            if (whichShape < 0 || whichShape > 3) {
                                System.out.println("Please enter a number between 1 and 3.");
                            } else {
                                inputValid = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("This input is not a number - please try again.");
                        }

                        obstructionCounter++;
                    }


                    // Ask for height of obstruction
                    double obstructionHeight = 0;
                    inputValid = false;
                    while (!inputValid) {
                        System.out.println("Please enter the height of the obstruction: ");
                        try {
                            obstructionHeight = Double.parseDouble(input.nextLine());
                            if (obstructionHeight < 0) {
                                System.out.println("Please enter a positive number.");
                            } else {
                                inputValid = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("This input is not a number - please try again.");
                        }
                    }

                    // Ask for width of obstruction
                    double obstructionWidth = 0;
                    inputValid = false;
                    while (!inputValid) {
                        System.out.println("Please enter width of the obstruction: ");
                        try {
                            obstructionWidth = Double.parseDouble(input.nextLine());
                            if (obstructionWidth < 0) {
                                System.out.println("Please enter a positive number.");
                            } else {
                                inputValid = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("This input is not a number - please try again.");
                        }
                    }

                    double currentObstructionArea;
                    if (whichShape == 1) {
                        currentObstructionArea = squareArea(obstructionWidth, obstructionHeight);
                    } else if (whichShape == 2) {
                        currentObstructionArea = circleArea(obstructionWidth, obstructionHeight);
                    } else {
                        currentObstructionArea = triangleArea(obstructionWidth, obstructionHeight);
                    }

                    totalObstructionArea += currentObstructionArea;
                }

                System.out.println("The total obstruction area on this wall is: " + totalObstructionArea +
                        (measurementSystem == 1 ? " metres squared" : " square feet"));
            }
            obstructionCounter = 1;

            // Calculate total area of wall - if they have obstructions, minus from total wall area
            totalWallArea = totalWallArea + ((height * width) - totalObstructionArea);
            walls[wallCounter-1] = "Wall " + wallCounter + " has an area of " + totalWallArea;

            wallCounter++;
            numberOfWalls--;
        } while (numberOfWalls != 0);

        for(int i = 0; i < wallCounter - 1 ; i++) {
            System.out.println(walls[i]);
        }
        System.out.println("The total wall area to paint minus the obstruction(s) is: " + totalWallArea +
                (measurementSystem == 1 ? " metres squared" : " square feet"));

        // Divide wall area by paint bucket coverage and round up to get total number of paint buckets
        double divisionResult;
        if (measurementSystem == 1) {
            divisionResult = divisionResult(litrePaintBucketCoverage, totalWallArea, numberOfCoats);
        } else {
            divisionResult = divisionResult(gallonPaintBucketCoverage, totalWallArea, numberOfCoats);
        }
        
        numberOfPaintBuckets = Math.ceil(divisionResult);
        System.out.println("----------------------------------------------------");
        System.out.println("You would need " + numberOfPaintBuckets + " paint buckets, each containing " + nameOfBucketSize
                + " to apply " + numberOfCoats + " coat(s) on the wall(s).");
        System.out.println("With each paint bucket costing " +
                (measurementSystem == 1 ? String.format("%.2f", Double.parseDouble(costOfLitrePaintBucket[1][bucketSizeSelection-1])) :
                        String.format("%.2f", Double.parseDouble(costOfGallonPaintBucket[1][bucketSizeSelection-1]))) +
                ", this would cost you " +
                (measurementSystem == 1 ? String.format("%.2f", Double.parseDouble(costOfLitrePaintBucket[1][bucketSizeSelection-1]) * numberOfPaintBuckets) + "." :
        String.format("%.2f", Double.parseDouble(costOfGallonPaintBucket[1][bucketSizeSelection-1]) * numberOfPaintBuckets) + "."));
        System.out.println("After painting the walls, you will have " +
                String.format("%.2f", (numberOfPaintBuckets - divisionResult) * bucketSizeSelection) + " " +
                (measurementSystem == 1 ? "litres" : "gallons") + " of paint left over.");
    }

    public static double squareArea(double x, double y) {
        return x * y;
    }

    public static double circleArea(double x, double y) {
        return 3.14 * x * y;
    }

    public static double triangleArea(double x, double y) {
        return (x * y) / 2;
    }

    public static double divisionResult(double coverage, double totalWallArea, double numberOfCoats) {
        return (coverage > 0) ? (totalWallArea / coverage) * numberOfCoats : 0.0;
    }
}
