<h2><a href="https://www.geeksforgeeks.org/problems/possible-paths-between-2-vertices-1587115620/1">Count Paths Between Two Vertices</a></h2><h3>Difficulty Level : Difficulty: Medium</h3><hr><div class="problems_problem_content__Xm_eO" style="--text-color: var(--problem-text-color);"><p><span style="font-size: 14pt;">Given a Directed Acyclic Graph (DAG) with V vertices (numbered 0 to V-1) and a list of directed edges edges[][], where each edges[i] = [u, v] represents a directed edge from vertex u to vertex v. The graph has no self-loops or multiple edges. Given two vertices src and dest, find the total number of distinct paths from src to dest.</span></p><p><span style="font-size: 14pt;"><strong>Examples:</strong></span></p><pre><span style="font-size: 14pt;"><strong>Input: </strong>V = 5, edges[][] = [[0, 1], [0, 2], [0, 4], [1, 3], [1, 4], [2, 4], [3, 2]], src = 0, dest = 4
<strong>Output:</strong> 4
<strong>Explanation</strong>: 
<img src="https://media.geeksforgeeks.org/img-practice/prod/addEditProblem/701246/Web/Other/blobid0_1752143630.jpg" width="327" height="217"><br>There are 4 paths from 0 to 4.
0 -&gt; 4
0 -&gt; 1 -&gt; 4
0 -&gt; 2 -&gt; 4
0 -&gt; 1 -&gt; 3 -&gt; 2 -&gt; 4
</span></pre><pre><span style="font-size: 14pt;"><strong>Input: </strong>V = 4, edges[][] = [[0, 1], [0, 3], [1, 2], [1, 3], [2, 3]], src = 0, dest = 3
<strong>Output:</strong> 3
<strong>Explanation</strong>:
<img src="https://media.geeksforgeeks.org/img-practice/prod/addEditProblem/701246/Web/Other/blobid1_1752143671.jpg" width="307" height="204"></span><br><span style="font-size: 14pt;">There are 3 paths from 0 to 3.
0 -&gt; 3
0 -&gt; 1 -&gt; 3
0 -&gt; 1 -&gt; 2 -&gt; 3
</span></pre><p><span style="font-size: 14pt;"><strong>Constraints:<br></strong>1 ≤ V ≤ 10<sup>5<br></sup>1 ≤ edges.size() ≤ 10<sup>5</sup><br>0 ≤ edges[i][0], edges[i][1] ≤ V-1<br>0 ≤ src, dest ≤ V-1</span></p></div><br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>DFS</code>&nbsp;<code>Graph</code>&nbsp;