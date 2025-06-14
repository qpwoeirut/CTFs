# Dragon's Fury Simulation

In the epic battle against Malakar's dark forces, the ancient dragons must unleash a series of precise attacks. Each attack round offers several potential damage values—but only one combination of attacks will sum up exactly to the damage required to vanquish the enemy.

## Your Task
Read a single string that represents a list of subarrays. Each subarray contains possible damage values for one round.
Example:
```
[[13, 15, 27, 17], [24, 15, 28, 6, 15, 16], [7, 25, 10, 14, 11], [23, 30, 14, 10]]
```

Read an integer `T` that represents the target total damage required.
Pick exactly one number from each subarray so that their sum equals `T`.
It is guaranteed that there is exactly one valid solution.
Output the valid combination (as a list) that meets the target damage.

## Example
### Input
```
[[13, 15, 27, 17], [24, 15, 28, 6, 15, 16], [7, 25, 10, 14, 11], [23, 30, 14, 10]]
77
```

### Output
```
[13, 24, 10, 30]
```

### Explanation:
The input represents 4 rounds of attacks, each with a list of possible damage values. The unique valid solution is the combination
```
[13, 24, 10, 30]
```
which sums exactly to 77.