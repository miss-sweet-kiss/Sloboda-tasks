import java.util.*;
/*You are given a list of cities. Each direct connection between two cities has its transportation 
 * cost (an integer bigger than 0). The goal is to find the paths of minimum cost between pairs 
 * of cities. Assume that the cost of each path (which is the sum of costs of all direct 
 * connections belonging to this path) is at most 200000. The name of a city is a string 
 * containing characters a,...,z and is at most 10 characters long.2)*/

public class Cities {
	
	//Dijkstra
	public static int shortestPath(Integer[][] matrix, Integer firstCity, Integer lastCity) {
		Boolean[] mark = new Boolean[matrix.length];//array of marks
		Integer[] path = new Integer[matrix.length];//distance array
		
		for(int i = 0;i < matrix.length;i++)
			path[i] = 200000;				//set distance to all vertices 200000
		path[firstCity] = 0;				//for the initial vertex set 0
		
		for(int i = 0;i < matrix.length;i++)
			mark[i] = false;				//set mark to all vertices false
		
		for(;;) {
			int v = -1;
			for(int nv = 0;nv < matrix.length;nv++) //iterate through the vertices
				if(!mark[nv] && path[nv] < 20000 && (v == -1 || path[v] > path[nv]))
					v = nv;		//choose the closest unmarked vertex 
			if(v == -1)
				break;		//nearest vertex not found
			mark[v] = true;	//mark it
			for(int nv = 0;nv < matrix.length;nv++) //for all unmarked adjacent 
				if(!mark[nv] && matrix[v][nv] < 200000) {
					if(path[nv] > path[v] + matrix[v][nv])
						path[nv] = path[v] + matrix[v][nv];	//improve the estimate of the distance
				}
				
		}	
		return path[lastCity];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String cityName = new String();
		String citySource = new String();
		String cityDest = new String();
		int testCount, cityCount, id, cost, count = 0, paths, citySourceId = 0, cityDestId = 0;
		ArrayList<City> cities;

        System.out.println("Enter the number of tests: ");
        testCount = sc.nextInt();
        for(int k = 0;k < testCount;k++){
        	cities = new ArrayList<City>();
        	System.out.println("Enter the number of cities: ");
        	cityCount = sc.nextInt();
        	for(int l = 0;l < cityCount;l++){
        		System.out.println("Enter city name: ");
        		cityName = sc.next();
        		System.out.println("Enter the number of neighbours of city "+cityName+": ");
        		count = sc.nextInt(); 
        		HashMap<Integer, Integer> cityCost = new HashMap<Integer, Integer>();
        		for(int m = 0;m < count;m++){
        			System.out.println("Enter index of a city connected to "+cityName+": ");
        			id = sc.nextInt();
        			System.out.println("Enter the transportation cost: ");
        			cost = sc.nextInt();
        			cityCost.put(id, cost);
        		}
        		City city = new City(l+1, cityName, cityCost);
        		cities.add(city);
        	}
        	Integer[][] matrix = City.createMatrix(cities);
        	System.out.println("Enter the number of paths to find: ");
        	paths = sc.nextInt();
        	for(int i = 0;i < paths;i++) {
        		System.out.println("Enter city-source: ");
        		citySource = sc.next();
        		System.out.println("Enter the city-destination: ");
        		cityDest = sc.next();
        		for(int j = 0;j < cities.size();j++) {
            		if(cities.get(j).getName().equals(citySource))
            			citySourceId = cities.get(j).getId();
            		if(cities.get(j).getName().equals(cityDest))
            			cityDestId = cities.get(j).getId();
            	}
        		System.out.println("The shortest path = " + shortestPath(matrix, citySourceId-1, cityDestId-1));
        	}
        }
        

	}

}

class City {
	int id;
	String name;
	HashMap<Integer, Integer> cityCost = new HashMap<Integer, Integer>();
	
	City(int id, String name, HashMap<Integer, Integer> cityCost) {
		this.id = id;
		this.name = name;
		this.cityCost = cityCost;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public Set<Integer> getNeighborhood() {		
		return cityCost.keySet();
	}
	
	public Integer getCost(Integer key) {
		return cityCost.get(key);
	}
	
	public String toString() {
		return id + " " + name + " " + cityCost;
	}
	
	public static Integer[][] createMatrix(ArrayList<City> cities) {
		Integer[][] matrix = new Integer[cities.size()][cities.size()];
		for(int i = 0;i < cities.size();i++){
			for(int j = 0;j < cities.size();j++){
				matrix[i][j] = 200000;
			}
		}
		for(int i = 0;i < cities.size();i++){
			Iterator<Integer> iter = cities.get(i).getNeighborhood().iterator();
			while(iter.hasNext()){
				int key = iter.next();
				matrix[cities.get(i).getId()-1][key-1] = cities.get(i).getCost(key);
			}
		}
		return matrix;
	}
	
}
