<h2><a href="https://www.geeksforgeeks.org/problems/maximum-weight-node--170645/1">Maximum Weight Node</a></h2><h3>Difficulty Level : Difficulty: Easy</h3><hr><div class="problems_problem_content__Xm_eO" style="--text-color: var(--problem-text-color);"><p><span style="font-size: 18px;">Given a maze with n cells. Each cell may have multiple entry points but not more than one exit point (i.e entry/exit points are unidirectional doors like valves).</span></p>
<p><span style="font-size: 18px;">You are given an array <strong>exits[]</strong>,&nbsp;where exits[i] contains the exit point of the ith cell. If exits[i] is -1, then ith cell doesn't have an exit. </span></p>
<p><span style="font-size: 18px;">The task is to find the cell with&nbsp;maximum weight (The weight of a cell i is the sum of all the cell indexes that have exit point as i ). If there are multiple cells with the maximum weight return the cell with highest index.</span></p>
<p><span style="font-size: 18px;"><strong>Note:&nbsp;</strong>The cells are indexed with an integer value from 0 to n-1. A cell i has weight 0 if no cell has exit point as i.</span></p>
<p><span style="font-size: 18px;"><strong><strong>Examples:</strong></strong></span></p>
<pre><span style="font-size: 18px;"><strong><strong>Input: </strong></strong>exits[] = [2, 0, -1, 2}<strong>
<strong>Output:</strong> </strong>2<strong>
<strong>Explanation</strong>: 
</strong>1 -&gt; 0 -&gt; 2 &lt;- 3
weight of 0th cell = 1
weight of 1st cell = 0 (because there is no cell pointing to the 1st cell)
weight of 2nd cell = 0+3 = 3
weight of 3rd cell = 0
There is only one cell which has maximum weight (i.e 2)<br>So, cell 2 is the output.</span></pre>
<pre><span style="font-size: 18px;"><strong><strong>Input: </strong></strong>exits[] = [-1]<strong>
<strong>Output:</strong> </strong>0<strong>
<strong>Explanation</strong>:
</strong>weight of 0<sup>th</sup> cell is 0.
There is only one cell so cell 0 has maximum weight.
</span></pre>
<p><span style="font-size: 18px;"><strong><strong>Constraints:</strong></strong><br>1 &nbsp;≤ &nbsp;n &nbsp;≤ &nbsp;10<sup>5</sup><br>-1 &nbsp;<u>&lt;</u>&nbsp; exits[i] &nbsp;&lt;&nbsp; n<br>exits[i] &nbsp;≠ &nbsp;i</span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>JUSPAY</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Arrays</code>&nbsp;<code>Graph</code>&nbsp;