/** Represents a user in a social network. A user is characterized by a name,
 *  a list of user names that s/he follows, and the list's size. */
 public class User {
    public static void main(String[] args) {
        User yoad = new User("yoad", true);

        yoad.addFollowee("Tal");
        yoad.addFollowee("Maya");

        yoad.removeFollowee("Foo");

        

        User ori = new User("ori", true);
        ori.addFollowee("Tal");
        ori.addFollowee("Eylon");
        ori.addFollowee("Uri");
        ori.addFollowee("yoad");
        

        yoad.addFollowee("Eylon");
        yoad.removeFollowee("Bar");
        yoad.addFollowee("ori");
        


        

        System.out.println(yoad.toString());
        System.out.println(ori.toString());
        System.out.println(yoad.countMutual(ori));
        System.out.println(ori.countMutual(yoad));
        System.out.println(ori.isFriendOf(yoad));

        System.out.println(yoad.follows("ori"));




    }

    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name;       // name of this user
    private String[] follows;  // array of user names that this user follows
    private int fCount;        // actual number of followees (must be <= maxfCount)

    /** Creates a user with an empty list of followees. */
    public User(String name) {
        this.name = name;
        follows = new String[maxfCount]; // fixed-size array for storing followees
        fCount = 0;                      // initial number of followees
    }

    /** Creates a user with some followees. The only purpose of this constructor is 
     *  to allow testing the toString and follows methods, before implementing other methods. */
    public User(String name, boolean gettingStarted) {
        this(name);
        follows[0] = "Foo";
        follows[1] = "Bar";
        follows[2] = "Baz";
        fCount = 3;
    }

    /** Returns the name of this user. */
    public String getName() {
        return name;
    }

    /** Returns the follows array. */
    public String[] getfFollows() {
        return follows;
    }

    /** Returns the number of users that this user follows. */
    public int getfCount() {
        return fCount;
    }

    /** If this user follows the given name, returns true; otherwise returns false. */
    public boolean follows(String name) {
        boolean isf = false;
        for (int i = 0; i < this.getfCount(); i++) {
            if (this.getfFollows()[i].equals(name)){
                isf = true;
                break;
            }
            else {
                isf = false;
            }
        
        }
        return isf;
        

    }
    /** Makes this user follow the given name. If successful, returns true. 
     *  If this user already follows the given name, or if the follows list is full, does nothing and returns false; */
    public boolean addFollowee(String name) {
       
        if (this.follows(name) || this.getfCount() >= 10){
            return false;
        }
       
        else{
            follows[this.getfCount()] = name;
            this.fCount++;
            return true;
        }

    }

    /** Removes the given name from the follows list of this user. If successful, returns true.
     *  If the name is not in the list, does nothing and returns false. */
    public boolean removeFollowee(String name) {
        String[] newFollows = new String[maxfCount-1];
        if (!this.follows(name) || this.getfCount() == 0){
            return false;
        }
        else{
            int count = 0;
            for (int i = 0; i < getfCount()-1; i++) {
                if (this.getfFollows()[i].equals(name)){
                    this.follows[i] = this.getfFollows()[this.getfCount()-1];
                    this.follows[this.getfCount()-1] = null;
                }
            }
            this.fCount--;
            return true;
        }

    }

    /** Counts the number of users that both this user and the other user follow.
    /*  Notice: This is the size of the intersection of the two follows lists. */
    public int countMutual(User other) {
        int fcountMutual = 0;
        for (int i = 0; i < Math.min(this.getfCount(), other.getfCount()); i++) {
            for (int j = 0; j < Math.max(this.getfCount(), other.getfCount()); j++) {
                if (this.getfFollows()[i].equals(other.getfFollows()[j])){
                    fcountMutual++;
                    
                }
            }

            
        }
        return fcountMutual;
    }

    /** Checks is this user is a friend of the other user.
     *  (if two users follow each other, they are said to be "friends.") */
    public boolean isFriendOf(User other) {
            if (this.follows(other.getName())&&other.follows(this.getName())) {
                return true;
            }
            else{
                return false;
            }
        
    }
    /** Returns this user's name, and the names that s/he follows. */
    public String toString() {
        String ans = name + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }
        return ans;
    }
}
