// Java solution to Q721 "Account Merge"
// Reference: https://leetcode.com/problems/accounts-merge/description/
// Date: 11-1-2018

/**
 * Thoughts:
 * 1) We need to find a data structure that can effectively join two different
 * group of data together by keys of data ==> This makes me consider to use
 * union find on this question.
 * 2) Since, as the problem states, there might be different people with same name,
 * so the "keys" that we are going to use in union find won't be names, but emails
 * 3) We also need to way to quickly find the account associated with the email,
 * so I am considering to also use a HashMap for key-value pair look up.
 * 4) I am going to create helper class to store the information that read from each
 * account: the name of account owner, as well as the emails listed in that account.
 * This class will implement the merge operation
 *
 * *****************************************************************************
 * 九章算法强化班的思路 （参见九章算法版第二节课下半部分笔记开头部分）：
 * 1) 通常来说，对于这样的问题，我们往往是将owner name当作index(类似于SQL中的primary key)
 * 即一个owner name名下对应了所有的email (正向索引 forward index)
 * e.g. John : [[john1@gmail.com], [john2@gmail.com], [john3@gmail.com]]
 * 2）那么反过来说我们可不可以设立一个inverted index，即每个email对应了所有与其关联的
 * 人的ID(这里我们可以使用一个小技巧将string name转化为int index, 使得union find更好处理)
 * e.g. john1@gmail.com : [[1, 2, 3]]  "1" => John "2" => "Johnson" "3" => "John"
 *（反向索引 inverted index）
 * 3) 接下来我们可以将反向索引得到的index table翻转过来，变为正向索引，即我们要将每一个email
 * 对应的index union到一起
 * 4) 接下来我们遍历一遍input list，对于其中每一个user的index，我们在第三部生成的union find中
 * 找到其的root index，将所有的email合并到root index对应的email set中
 * 5）接下来我们只要将第四步中得到的map的key index转换成对应的user name即可输出最终结果
 *
 * 小技巧：利用input list中每一个account本身在list中的index作为新的index，这样方便了之后
 * 回到原input中lookup的过程
 */

/*
Given a list accounts, each element accounts[i] is a list of strings,
where the first element accounts[i][0] is a name, and the rest of the elements
are emails representing emails of the account.

Now, we would like to merge these accounts.
Two accounts definitely belong to the same person if there is some email that is common to both accounts.
Note that even if two accounts have the same name, they may belong to different people
as people could have the same name. A person can have any number of accounts initially,
but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format:
the first element of each account is the name, and the rest of the elements are emails in sorted order.
The accounts themselves can be returned in any order.

Example 1:
Input:
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
["John", "johnnybravo@mail.com"],
["John", "johnsmith@mail.com", "john_newyork@mail.com"],
["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Explanation:
The first and third John's are the same person as they have the common email "johnsmith@mail.com".
The second John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'],
['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
Note:

The length of accounts will be in the range [1, 1000].
The length of accounts[i] will be in the range [1, 10].
The length of accounts[i][j] will be in the range [1, 30].
 */

/**
 * Refactor the solution: now will convert string owner name into id (just like)
 *
 * @author Haoyang Fan
 * @version 2.0
 * @since 11-4-2018
 */
class UnionFind {
    protected int[] parent;
    protected int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int p) {
        if (p == parent[p]) {
            return p;
        }
        parent[p] = find(parent[p]);
        return parent[p];
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        int rankP = rank[rootP];
        int rankQ = rank[rootQ];
        if (rankP < rankQ) {
            parent[rootP] = rootQ;
            rank[rootQ] += rank[rootP];
        } else {
            parent[rootQ] = rootP;
            rank[rootP] += rank[rootQ];
        }
    }
}

class Solution {
    private static final int MAX_NUM_EMAIL = 10;
    private static final int MAX_NUM_ACCOUNT = 1000;

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> rst = new ArrayList<>(MAX_NUM_ACCOUNT);

        // step 2: establish a inverted index table (email => id)
        Map<String, List<Integer>> email_to_id = new HashMap<>(MAX_NUM_ACCOUNT * MAX_NUM_EMAIL);
        for (int user_id = 0; user_id < accounts.size(); user_id++) {
            List<String> account = accounts.get(user_id);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                List<Integer> l = email_to_id.getOrDefault(email, new ArrayList<Integer>());
                l.add(user_id); // add user id
                email_to_id.put(email, l);
            }
        }

        // step 3: union the ids that are associated with same email
        UnionFind uf = new UnionFind(accounts.size());
        for (List<Integer> ids : email_to_id.values()) {
            // union each id with previous id
            for (int i = 1; i < ids.size(); i++) {
                uf.union(ids.get(i), ids.get(i-1));
            }
        }

        // step 4: establish the forward table (id => email)
        Map<Integer, Set<String>> id_to_email = new HashMap<>(MAX_NUM_ACCOUNT);
        for (int user_id = 0; user_id < accounts.size(); user_id++) {
            List<String> account = accounts.get(user_id);
            // for each id of accounts, find the root if of its component
            int root_id = uf.find(user_id);
            // use TreeSet to sort the email
            Set<String> emails = id_to_email.getOrDefault(root_id, new TreeSet<String>());
            for (int j = 1; j < account.size(); j++) {
                emails.add(account.get(j));
            }
            id_to_email.put(root_id, emails);
        }

        // step 5: almost done, just find user name corresponding to each root id
        for (int root_id : id_to_email.keySet()) {
            String username = accounts.get(root_id).get(0);
            List<String> user_emails = new ArrayList<>();
            // add the username to the head
            user_emails.add(username);
            // add the emails to the end
            user_emails.addAll(id_to_email.get(root_id));
            rst.add(user_emails);
        }
        return rst;
    }
}

/*----------------------------------------------------------------------------*/

/**
 * Initial union find soluition in Java
 *
 * @author Haoyang Fan
 * @version 1.0
 * @since 11-1-2018
 */
class Node {
    protected String owner;
    protected List<String> emails = new ArrayList<>(10);

    public Node(String name, String email) {
        owner = name;
        emails.add(email);
    }

    public void merge(Node other) {
        emails.addAll(other.emails);
    }

    public List<String> toList() {
        List<String> l = new ArrayList<>(emails.size() + 1);
        l.add(owner);
        Collections.sort(emails);
        l.addAll(emails);
        return l;
    }
}

class UnionFind {
    Map<String, Node> lookup;   // address book
    Map<String, String> parent;    // union find base data structure
    Map<String, Integer> rank;

    public UnionFind(List<List<String>> accounts) {
        lookup = new HashMap<>(10000);
        parent = new HashMap<>(10000);
        rank = new HashMap<>(10000);
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                lookup.putIfAbsent(email, new Node(name, email));   // email -> Node (email, name)
                parent.putIfAbsent(email, email);   // email -> email
                rank.putIfAbsent(email, 1);
                if (i == 1) {
                    continue;
                }
                // union with previous email
                union(email, account.get(i-1));
            }
        }
    }

    public List<List<String>> getResult() {
        List<List<String>> rst = new ArrayList<>();
        // iterate through all emails
        for (String email : parent.keySet()) {
            // check if this email is the root of connected component
            if (email.equals(find(email))) {
                rst.add(lookup.get(email).toList());
            }
        }
        return rst;
    }

    public String find(String p) {
        String root = p;
        while (!root.equals(parent.get(root))) {
            root = parent.get(root);
        }
        while (!p.equals(root)) {
            String father = parent.get(p);
            parent.put(father, root);
            p = father;
        }
        return root;
    }
    // the account merge also happens here
    public void union(String p, String q) {
        String rootP = find(p);
        String rootQ = find(q);
        if (rootP.equals(rootQ)) {
            return;
        }
        int rankP = rank.get(rootP);
        int rankQ = rank.get(rootQ);
        if (rankP < rankQ) {
            parent.put(rootP, rootQ);
            rank.put(rootQ, rankP + rankQ);
            lookup.get(rootQ).merge(lookup.get(rootP));
        } else {
            parent.put(rootQ, rootP);
            rank.put(rootP, rankP + rankQ);
            lookup.get(rootP).merge(lookup.get(rootQ));
        }
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind uf = new UnionFind(accounts);
        return uf.getResult();
    }
}
