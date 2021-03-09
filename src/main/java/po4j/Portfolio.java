package po4j;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

public class Portfolio {

	private Map<String, Double> allocation;
	private double riskValue;
	private double returnValue;
	private double bestValue;

	public Portfolio() {

	}

	public Portfolio(Map<String, Double> allocation, double riskValue, double returnValue) {
		super();
		this.allocation = allocation;
		this.riskValue = riskValue;
		this.returnValue = returnValue;
		this.bestValue = riskValue / returnValue;
	}

	public Map<String, Double> getAllocation() {
		return allocation;
	}

	public void setAllocation(Map<String, Double> allocation) {
		this.allocation = allocation;
	}

	public double getRiskValue() {
		return riskValue;
	}

	public void setRiskValue(double riskValue) {
		this.riskValue = riskValue;
	}

	public double getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(double returnValue) {
		this.returnValue = returnValue;
	}

	public double getBestValue() {
		return bestValue;
	}

	@Override
	public String toString() {
		DecimalFormat df2 = new DecimalFormat("0.000");
		String s = "";

		this.allocation = MapUtil.sortByValue(this.allocation);

		Set<String> key = allocation.keySet();
		for (String m : key) {
			s += m + " ";
		}
		double er = roundAvoid(returnValue * 100, 3);
		double risk = roundAvoid(Math.sqrt(riskValue) * 100, 3);

		s = s + " E(r): " + df2.format(er) + " Risk: " + df2.format(risk) + "\n";

		return s;
	}

	public static double roundAvoid(double value, int places) {
		double scale = Math.pow(10, places);
		return Math.round(value * scale) / scale;
	}

	public static Comparator<Portfolio> byHigherReturnValue = new Comparator<Portfolio>() {
		public int compare(Portfolio one, Portfolio two) {
			return one.getReturnValue() > two.getReturnValue() ? 1
					: one.getReturnValue() < two.getReturnValue() ? -1 : 0;
		}
	};

	public static Comparator<Portfolio> byLessRisk = new Comparator<Portfolio>() {
		public int compare(Portfolio one, Portfolio two) {
			return one.getRiskValue() < two.getRiskValue() ? 1 : one.getRiskValue() > two.getRiskValue() ? -1 : 0;
		}
	};

}
