package com.test.jianjiazheng;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Positional {
    
    static boolean DEBUG = false;
    
    static String[][] intersectTestCases = {
            {"1:7,18,33,72,86,231; 2:1,17,74,222,255; 4:8,16,190,429,433; 5:363,367; 7:13,23,191; 13:28",
                    "1:17,25; 4:17,191,291,430,434; 5:14,19,101; 6:19; 8:42; 10:11; 13:24",
                    "[(1,18,17), (4,16,17), (4,190,191), (4,429,430), (4,429,434), (4,433,430), (4,433,434), (13,28,24)]"
            },
            {"1:11,35,77,98,104; 5:100",
                    "1:21,92,93,94,95,97,99,100,101,102,103,105,106,107,108,109,110; 5:94,95",
                    "[(1,98,93), (1,98,94), (1,98,95), (1,98,97), (1,98,99), (1,98,100), (1,98,101), (1,98,102), (1,98,103), (1,104,99), (1,104,100), (1,104,101), (1,104,102), (1,104,103), (1,104,105), (1,104,106), (1,104,107), (1,104,108), (1,104,109), (5,100,95)]"
            },
            {"1:1,2,3,4,5,6,7",
                    "1:1,2,3,4,5,6,7",
                    "[(1,1,1), (1,1,2), (1,1,3), (1,1,4), (1,1,5), (1,1,6), (1,2,1), (1,2,2), (1,2,3), (1,2,4), (1,2,5), (1,2,6), (1,2,7), (1,3,1), (1,3,2), (1,3,3), (1,3,4), (1,3,5), (1,3,6), (1,3,7), (1,4,1), (1,4,2), (1,4,3), (1,4,4), (1,4,5), (1,4,6), (1,4,7), (1,5,1), (1,5,2), (1,5,3), (1,5,4), (1,5,5), (1,5,6), (1,5,7), (1,6,1), (1,6,2), (1,6,3), (1,6,4), (1,6,5), (1,6,6), (1,6,7), (1,7,2), (1,7,3), (1,7,4), (1,7,5), (1,7,6), (1,7,7)]"
            },
            {"1:11,92; 17:6,16; 21:103,113,114",
                    "4:8; 5:2; 17:11; 21:3, 97,108",
                    "[(17,6,11), (17,16,11), (21,103,108), (21,113,108)]"
            },
            {"5:4; 11:7,18; 12:1,17; 14:8,16; 15:363,367; 7:13,23,191; 103:28",
                    "3:2; 8:9; 11:17,25; 14:17,434; 15:101; 16:19; 18:42; 100:11; 103:24; 109:11",
                    "[(11,18,17), (14,16,17), (103,28,24)]"
            },
            {"1:1; 5:1; 11:1; 13:1; 19:1; 43:1",
                    "2:1; 3:1; 5:1; 9:1; 11:1; 15:1; 19:1; 33:1; 45:1",
                    "[(5,1,1), (11,1,1), (19,1,1)]"
            },
            {"1:1",
                    "2:1; 189:10",
                    "[]"
            }
        
    };
    
    /**
     * Stores the Posting for a single document: a docID and optionally a list of document positions.
     */
    static class Posting {
        int docID;
        List<Integer> positions;
        
        public Posting(int docID, List<Integer> positions) {
            this.docID = docID;
            this.positions = positions;
        }
        
        public Iterator<Integer> positions() {
            return positions.iterator();
        }
        
        public String toString() {
            return docID + ":" + positions;
        }
    }
    
    
    static class AnswerElement {
        int docID;
        int p1pos;
        int p2pos;
        
        AnswerElement(int docID, int p1pos, int p2pos) {
            this.docID = docID;
            this.p1pos = p1pos;
            this.p2pos = p2pos;
        }
        
        public String toString() {
            return "(" + docID + "," + p1pos + "," + p2pos + ")";
        }
    }
    
    
    /**
     * Find proximity matches where the two words are within k words in the two postings lists
     * Returns a list of (document, position_of_p1_word, position_of_p2_word) items.
     */
    static List<AnswerElement> positionalIntersect(Iterator<Posting> p1, Iterator<Posting> p2, int k) {
        List<AnswerElement> answer = new ArrayList<>();
        //Posting p1posting = popNextOrNull(p1);
        //Posting p2posting = popNextOrNull(p2);
        // TODO WRITE THE ALGORITHM HERE!
        /* Why use the fucking thick of fucking popNextOrNull() , I think that useless for index*/
        
        List<Posting> p1pList = new ArrayList<>();
        List<Posting> p2pList = new ArrayList<>();
        
        while (p1.hasNext()) {
            p1pList.add(p1.next());
        }
        while (p2.hasNext()) {
            p2pList.add(p2.next());
        }
        
        int i = 0, j = 0;
        int p1pSize = p1pList.size();
        int p2pSize = p2pList.size();
        while (i < p1pSize & j < p2pSize) {
            Posting p1posting = p1pList.get(i);
            Posting p2posting = p2pList.get(j);
            int docID1 = p1posting.docID, docID2 = p2posting.docID;
            if (docID1 == docID2) {
                List<Integer> l = new ArrayList<>();
                int i1 = 0, j1 = 0;
                List<Integer> pp1 = p1posting.positions;
                List<Integer> pp2 = p2posting.positions;
                while (i1 < pp1.size()) {
                    while (j1 < pp2.size()) {
                        if (Math.abs(pp1.get(i1) - pp2.get(j1)) <= k) {
                            l.add(pp2.get(j1));
                        } else if (pp2.get(j1) > pp1.get(i1)) {
                            break;
                        }
                        j1 = j1 + 1;
                    }
                    while (l.size() != 0 && Math.abs(l.get(0) - pp1.get(i1)) > k) {
                        l.remove(0);
                    }
                    for (int ps = 0; ps < l.size(); ps++) {
                        answer.add(new AnswerElement(docID1, pp1.get(i1), l.get(ps)));
                    }
                    i1 = i1 + 1;
                }
                i = i + 1;
                j = j + 1;
            } else if (docID1 < docID2) {
                i = i + 1;
            } else {
                j = j + 1;
            }
        }
        return answer;
    }
    
    /**
     * Returns the next item from the Iterator, or null if it is exhausted.
     * (This is a more C-like method than idiomatic Java, but we use it so as
     * to be more parallel to the pseudo-code in the textbook.)
     */
    static <X> X popNextOrNull(Iterator<X> p) {
        if (p.hasNext()) {
            return p.next();
        } else {
            return null;
        }
    }
    
    /**
     * Load a single postings list: Information about where a single token
     * appears in documents in the collection. This can load either a document
     * level posting which is a list of integer docID separated by semicolons
     * or a positional postings list, where each docID is followed by a colon
     * and then
     *
     * @param list A String representation of a postings list
     *
     * @return An Iterator over a {@code List<Posting>}
     */
    static Iterator<Posting> loadPostingsList(String list) {
        List<Posting> postingsList = new ArrayList<Posting>();
        String[] psts = list.split(";");
        for (String pst : psts) {
            String[] bits = pst.split(":");
            String[] poses = bits[1].split(",");
            int docID = Integer.valueOf(bits[0].trim());
            List<Integer> positions = new ArrayList<Integer>();
            for (String pos : poses) {
                positions.add(Integer.valueOf(pos.trim()));
            }
            Posting post = new Posting(docID, positions);
            postingsList.add(post);
        }
        if (DEBUG) {
            System.err.println("Loaded postings list: " + postingsList);
        }
        return postingsList.iterator();
    }
    
    /**
     * Main method. With no parameters, it runs some internal test cases.
     * With two postings list arguments, it or's the arguments given on the command line.
     * Otherwise, it will print a usage message.
     *
     * @param args Command-line arguments, as above.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            for (String[] test : intersectTestCases) {
                System.out.println("################### I Am Bad Auy [Start]######################");
                Iterator<Posting> pl1 = loadPostingsList(test[0]);
                Iterator<Posting> pl2 = loadPostingsList(test[1]);
                System.out.println("Intersection of: " + test[0]);
                System.out.println("            and: " + test[1]);
                List<AnswerElement> ans = positionalIntersect(pl1, pl2, 5);
                System.out.println("Answer---------: " + ans);
                if (!ans.toString().equals(test[2])) {
                    System.out.println("Should be------: " + test[2]);
                    System.out.println("*** ERROR ***");
                }
                System.out.println("################### I Am Bad Auy [ End ]######################");
                System.out.println("\n");
            }
        } else if (args.length != 2) {
            System.err.println("Usage: java Intersect postingsList1 postingsList2");
            System.err.println("       postingsList format: '1:17,25; 4:17,191,291,430,434; 5:14,19,10'");
        } else {
            Iterator<Posting> pl1 = loadPostingsList(args[0]);
            Iterator<Posting> pl2 = loadPostingsList(args[1]);
            List<AnswerElement> ans = positionalIntersect(pl1, pl2, 5);
            System.out.println(ans);
        }
    }
    
}
