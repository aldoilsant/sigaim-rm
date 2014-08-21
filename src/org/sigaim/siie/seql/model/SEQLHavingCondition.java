package org.sigaim.siie.seql.model;

public class SEQLHavingCondition {
		private SEQLEvaluable root;
		
		public SEQLHavingCondition() {
			
		}
		public SEQLEvaluable getRoot() {
			return root;
		}
		public void setRoot(SEQLEvaluable root) {
			this.root=root;
		}
		@Override public String toString() {
			return root+"";
		}
}
