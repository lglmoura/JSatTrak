/*
 *=====================================================================
 * Copyright (C) 2009 Shawn E. Gano
 * 
 * This file is part of JSatTrak.
 * 
 * JSatTrak is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * JSatTrak is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JSatTrak.  If not, see <http://www.gnu.org/licenses/>.
 * =====================================================================
 */

package name.gano.math.nonlinsolvers;

/**
 *
 * @author Shawn
 */
public class TestDC implements NonLinearEquationSystemProblem
{
    // main function
    public static void main(String args[])
    {
        TestDC prob = new TestDC();
        
        double[] fgoals = new double[] {0.0,0.0};
        
        double[] X0 = new double[] {0,0};
        
        //NonLinearEquationSystemSolver dc = new ModifiedNewtonFiniteDiffSolver(prob, fgoals, X0);
        NonLinearEquationSystemSolver dc = new ModifiedBroydenSolver(prob, fgoals, X0);
        dc.setVerbose(true);
        dc.solve();
        
        System.out.println("\n" +  dc.getOutputMessage());
        
    }
    
    
     public double[] evaluateSystemOfEquations(double[] x)
     {
         double[] f = new double[2];
         
//         f[0] = Math.sin(x[0]);
//         f[1] = Math.cos(x[1]) + 0.0;
         
//         f[0] = -2.0*x[0] + 3.0*x[1] -8.0;
//         f[1] =  3.0*x[0] - 1.0*x[1] + 5.0; 
         
//         f[0] = x[0] - x[1]*x[1];
//         f[1] = -2.0*x[0]*x[1] + 2.0*x[1];
         
         // himmelblau
//         f[0] = 4*x[0]*x[0]*x[0] + 4*x[0]*x[1]+2*x[1]*x[1] - 42*x[0] -14;
//         f[1] = 4*x[1]*x[1]*x[1] + 4*x[0]*x[1]+2*x[0]*x[0] - 26*x[1] -22;
         
         // Ferrais
         f[0] = 0.25/Math.PI*x[1]+0.5*x[0]-0.5*Math.sin(x[0]*x[1]);
         f[1] = Math.E/Math.PI*x[1]-2*Math.E*x[0]+(1-0.25/Math.PI)*(Math.exp(2*x[0])-Math.E);
         
         return f;
     }

}
