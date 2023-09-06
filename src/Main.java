import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        // 1,2,5 ----- 11
    }
}
//
//class Solution {
//    public int coinChange(int[] coins, int amount) {
//        int[] dp = new int [amount+1];
//        Arrays.fill(dp, amount+1);
//
//        dp[0] = 0;
//        for (int i = 1; i<= amount; i++){
//            for(int coin: coins){
//                if (i-coin < 0) continue;
//                dp[i] = Math.min(dp[i],dp[i-coin] + 1);
//            }
//        }
//        return dp[amount] == (amount + 1) ? -1 : dp[amount];
//    }
//}


//MEMOIZATION
class Solution {
    private Integer[] memo; // for memoization

    public int coinChange(int[] coins, int amount) {
        memo = new Integer[amount + 1]; // the +1 is because the purpose of the memor table is to
        // take a subproblem such as '9' and go through its recursive steps and
        // then record the min number of coins required to make that value
        // +1 is required to map the subproblem amount '9' to the correct index
        // table length of 10 = max index of 9. minCoins for subproblem 9 stored at
        // index 9
        return recursionHelper(coins, amount);
    }

    private int recursionHelper(int[] coins, int remain){
        if(remain<0) return -1;
        if(remain == 0)return 0;

        if(memo[remain] != null) return memo[remain]; // if subproblem has already been solved, just return it

        int minCount = Integer.MAX_VALUE;
        for(int coin: coins){ // will test all primary subproblems
            int count = recursionHelper(coins, remain-coin); // and will test all subproblems of the primary subproblems
            if(count == -1) continue; // if -1 base case reached, means the line of coins resulted in - remaining value. termiante.
            minCount = Math.min(minCount, count + 1); // +1 needed since base case is 0. need +1 indicates
            // the final coin that was used to reach base case.
        }

        memo[remain] = minCount == Integer.MAX_VALUE ? -1 : minCount; // store subproblem
        return memo[remain];
    }
}


// goal to avoid redundancies using dynamic programming and memoization. for example, in above set....
// amount of 11 and be reduced by 3 different coin options. 11 - 5, -2, -1. which creates subproblems
// 11 - 1 = 10 --> 10 - 1 = *9, 10-2 = *8; 10-5 = *5
// 11 - 2 = *9 --> 9-1 = *8, 9-2 = 7, 9-5 = 4
// 11 - 5 = 6 --> 6-1 = *5, 6-2 = 4, 6-5 = 1.
// note how subproblems with amount revised to 9,8,5 are redundant and the more we descend the tree and the bigger the
// amount and more coins, the more redundancies occur
// will ultimately reach base case of 0 or -1 where say we do 11-5-5-1 = 0 and finally 0 is the amount passed to base case
// and we determine that 0 coins are needed to make an amount of 0.
// each subproblem we must add + 1 coin since subproblems are 10,9,6. suppose 6 reuqires 2 coins, 5 and 1, = 2 coins. + 1 = 3

// here is brute force solution in which all redundancies are still tested

//class Solution {
//    public int coinChange(int[] coins, int amount) {
//        return recursionHelper(coins, amount);
//    }
//
//    private int recursionHelper(int[] coins, int remain){
//        if(remain<0) return -1;
//        if(remain == 0)return 0;
//
//        int minCount = Integer.MIN_VALUE;
//        for(int coin: coins){ // will test all primary subproblems
//            int count = recursionHelper(coins, remain-coin); // and will test all subproblems of the primary subproblems
//            if(count == -1) continue; // if -1 base case reached, means the line of coins resulted in - remaining value. termiante.
//            minCount = Math.min(minCount, count + 1);
//        }
//
//        return minCount == Integer.MAX_VALUE ? -1 : minCount; // if -1 assigned to count for all subproblems, then return -1,
//        // otherwise return the lowest number of coins
//    }
//}

//class Solution {
//    public int coinChange(int[] coins, int amount) {
//        Arrays.sort(coins);
//        int currIndex = coins.length - 1;
//        int coinCount = 0;
//
//        while (amount >= 0 & currIndex >= 0) {
//            if (amount - coins[currIndex] >= 0) {
//                amount -= coins[currIndex];
//                coinCount++;
//            } else {
//                currIndex--;
//            }
//        }
//
//        if (amount == 0) {
//            return coinCount;
//        } else {
//            return -1;
//        }
//    }
//} // why  this doesn't work.. asssume we have 2 coins: 5 and 3. Amount = 9.  9-5 = 4. 4-3 = 1. 1-3 is OOB. but obviously
// we can do 9-3-3-3 = 0. can't just work down from biggest to smallest


//class Solution {
//    public int coinChange(int[] coins, int amount) {
//        int currIndex = coins.length - 1;
//        int coinCount = 0;
//
//       return minusCoinValue(coins, amount, currIndex, coinCount);
//
//    }
//
//}
//
//    private int minusCoinValue(int[] coins, int amount, int currIndex, int count) {
//        if (amount - coins[currIndex] >= 0) {
//            amount -= coins[currIndex];
//            count++;
//            minusCoinValue(coins,amount,currIndex,count);
//        } else {
//            if (currIndex > 0) {
//                amount = minusCoinValue(coins, amount, currIndex--, count);
//            }
//        }
//        return count;
//    }
//}

// coins already sorted. need array where value at index is coin value. value will not change since not a limited 
// number of coins of a certain type
// don't need to make my own array, alrady exists

