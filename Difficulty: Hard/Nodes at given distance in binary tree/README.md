<h2><a href="https://www.geeksforgeeks.org/problems/nodes-at-given-distance-in-binary-tree/1">Nodes at given distance in binary tree</a></h2><h3>Difficulty Level : Difficulty: Hard</h3><hr><div class="problems_problem_content__Xm_eO" style="--text-color: var(--problem-text-color);"><p><span style="font-size: 14pt;">Given the <strong>root</strong> of a binary tree, the value of a <strong>target</strong> node, and an integer <strong>k</strong>, return all the nodes that are exactly k edges away from the target node. Return the node values in sorted order.</span></p>
<p><strong><span style="font-size: 14pt;">Note:</span></strong></p>
<ul>
<li><span style="font-size: 14pt;">The tree does not contain parent pointers.</span></li>
<li><span style="font-size: 14pt;">All node values are unique.</span></li>
<li><span style="font-size: 14pt;">The target node is guaranteed to be present in the tree.</span></li>
</ul>
<p><strong><span style="font-size: 18px;">Examples:</span></strong></p>
<pre><span style="font-size: 18px;"><strong>Input:</strong> root = [1, 2, 3, 4, 5], target = 2, k = 2   
</span><img src="https://media.geeksforgeeks.org/img-practice/prod/addEditProblem/700546/Web/Other/blobid0_1733895958.png" width="317" height="283">
<span style="font-size: 18px;"><strong>Output:</strong> [3]</span>
<span style="font-size: 18px;"><strong>Explanation: </strong>Nodes at a distance 2 from the given node 2 is 3.</span>
</pre>
<pre><span style="font-size: 14pt;"><strong>Input: </strong>root = [1, 2, 3, 4, 5, 6, 7], target = 3, k = 1<br><img src="https://media.geeksforgeeks.org/img-practice/prod/addEditProblem/700546/Web/Other/blobid1_1733896181.png" width="324" height="289">
</span><span style="font-size: 18px;"><span style="font-size: 14pt;"><strong>Output:</strong> [1, 6, 7]<br><strong style="font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;">Explanation:</strong><span style="font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;"> Nodes at a distance 1 from the given target node 3 are 1, 6 &amp; 7.</span></span></span></pre>
<p><span style="font-size: 18px;"></span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Flipkart</code>&nbsp;<code>Accolite</code>&nbsp;<code>Amazon</code>&nbsp;<code>Microsoft</code>&nbsp;<code>Samsung</code>&nbsp;<code>Hike</code>&nbsp;<code>Ola Cabs</code>&nbsp;<code>Walmart</code>&nbsp;<code>Goldman Sachs</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Tree</code>&nbsp;