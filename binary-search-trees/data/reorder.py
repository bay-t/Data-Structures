# By Todd Dole

def optimal_bst_order(lines):
    """
    Returns the lines reordered optimally for insertion into a binary search tree.
    """
    def bst_order(start, end):
        # Base case: when start is greater than end, return an empty list
        if start > end:
            return []
        # Find the middle index
        mid = (start + end) // 2
        # Recursively find the order of elements for left and right subtrees
        left = bst_order(start, mid - 1)
        right = bst_order(mid + 1, end)
        # Return the current element (root) followed by left and right subtree elements
        return [mid] + left + right

    # Get the optimal order of indices for BST insertion
    indices = bst_order(0, len(lines) - 1)
    # Use the indices to reorder the lines
    return [lines[i] for i in indices]

def reorder_file_for_bst(input_filename, output_filename):
    """
    Reads lines from the input file, reorders them for optimal BST insertion,
    and writes the reordered lines to the output file.
    """
    # Read the lines from the input file
    with open(input_filename, 'r') as file:
        lines = file.readlines()

    # Reorder the lines optimally for a BST
    reordered_lines = optimal_bst_order(lines)

    # Write the reordered lines to the output file
    with open(output_filename, 'w') as file:
        file.writelines(reordered_lines)

# Example usage
input_filename = 'data/geneva.txt'  # Replace with your input file name
output_filename = 'data/reordered_geneva.txt'  # Replace with your desired output file name
reorder_file_for_bst(input_filename, output_filename)