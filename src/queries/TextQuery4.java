package queries;


import iterators.Apply;
import iterators.ApplyFunction;
import iterators.Filter;
import iterators.FlatApply;
import iterators.FlatApplyFunction;
import iterators.Reduce;
import iterators.Predicate;
import iterators.ReduceFunction;
import readers.TextFileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// Return the number total occurences of the word "Mars"
public class TextQuery4 {
	public static void main(String[] args) {
		Iterator<Pair<String,String>> filenameAndContents = new TextFileReader("../sci.space");
		Iterator<String> contents = new Apply(new TakeRight<>(), filenameAndContents);
		Iterator<String> words = new FlatApply(new SplitBy(" "), contents); 
                Iterator<String> filter = new FlatApply(new flatCheck(), contents);
                
        /* finish the query */
                int count=0;
                
		while (filter.hasNext()) {
			count+=1;
		}
                
                System.out.println(count);
	}	

    /* Define the additional classes you need here */ 


	
	public static class TakeRight<L,R> implements ApplyFunction<Pair<L,R>, R> {
		@Override
		public R apply(Pair<L, R> x) {
			return x.right;	
		}
	}

	public static class SplitBy implements FlatApplyFunction<String, String> {
		private String ch;
		public SplitBy(String c) {
			this.ch = c;
		}
		@Override
		public List<String> apply(String x) {
			return Arrays.asList(x.split(ch));
		}
	}
        
        public static class flatCheck implements FlatApplyFunction<String, String> {
	
				@Override
				public List<String> apply(String x) {
					List<String> l = new LinkedList<>();
					if (x.equals("Mars")) {
						l.add(x.toString());
					}
					return l;
				}
		}
}
