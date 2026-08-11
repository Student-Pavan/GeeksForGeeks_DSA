<h2><a href="https://www.geeksforgeeks.org/problems/fixed-two-nodes-of-a-bst/1">Fixing Two nodes of a BST</a></h2><h3>Difficulty Level : Difficulty: Hard</h3><hr><div class="problems_problem_content__Xm_eO" style="--text-color: var(--problem-text-color);"><div>
<div>
<div>
<div><span style="font-size: 14pt;">Given the <strong>root</strong> of a Binary Search Tree (BST), where exactly two nodes have been swapped by mistake, restore the BST by swapping the values of the misplaced nodes. Return the root of the corrected BST.</span></div>
<div>&nbsp;</div>
<div><span style="font-size: 14pt;"><strong>Note:</strong> It is guaranteed that exactly two nodes have been swapped, and restoring their values will make the tree a valid BST. The structure of the tree must remain unchanged.</span></div>
</div>
</div>
</div>
<div>&nbsp;</div>
<div><span style="font-size: 18px;"><strong>Examples :</strong></span></div>
<pre><span style="font-size: 18px;"><strong style="font-size: 18px;">Input: </strong><span style="font-size: 18px;">root = [10, 5, 8, 2, 20]
     <img src="https://media.geeksforgeeks.org/img-practice/prod/addEditProblem/886490/Web/Other/blobid0_1738654776.png" alt="" width="256" height="236">
</span><strong style="font-size: 18px;">Output: </strong><span style="font-size: 18px;">[10, 5, 20, 2, 8]<br></span></span>       <img style="font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;" src="https://media.geeksforgeeks.org/img-practice/prod/addEditProblem/886490/Web/Other/blobid1_1738654776.png" alt="" width="255" height="235"><br><br><span style="font-size: 18px;"><strong>Explanation: </strong></span><span style="font-size: 18px;">The nodes 20 and 8 were swapped.</span><span style="font-size: 18px; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;"> </span></pre>
<pre><span style="font-size: 18px;"><strong style="font-size: 18px;">Input: </strong><span style="font-size: 18px;">root = [5, 10, 20, 2, 8]
     <img src="https://media.geeksforgeeks.org/img-practice/prod/addEditProblem/886490/Web/Other/blobid2_1738654931.png" alt="" width="254" height="226">
</span><strong style="font-size: 18px;">Output: </strong><span style="font-size: 18px;">[10, 5, 20, 2, 8]<br>     <img src="https://media.geeksforgeeks.org/img-practice/prod/addEditProblem/886490/Web/Other/blobid3_1738654931.png" alt="" width="249" height="228">
</span><strong style="font-size: 18px;">Explanation:</strong><span style="font-size: 18px;"> </span></span><span style="font-size: 18px;">The nodes 10 and 5 were swapped.</span></pre>
<p><span style="font-size: 18px;"><strong>Constraints:</strong></span><br><span style="font-size: 18px;">2 ≤ Number of nodes ≤ 10<sup>5<br></sup></span><span style="font-size: 14pt;">1 ≤ root[i] ≤ 10<sup>7</sup></span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Amazon</code>&nbsp;<code>Microsoft</code>&nbsp;<code>FactSet</code>&nbsp;<code>Walmart</code>&nbsp;<code>BankBazaar</code>&nbsp;<code>NPCI</code>&nbsp;<code>Paytm</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Binary Search Tree</code>&nbsp;