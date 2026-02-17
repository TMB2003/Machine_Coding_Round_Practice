// package com.tictactoe.factory;

// import com.tictactoe.models.Board;
// import com.tictactoe.strategies.WinStrategy;
// import com.tictactoe.strategies.RowWinStrategy;
// import com.tictactoe.strategies.ColumnWinStrategy;
// import com.tictactoe.strategies.DiagonalWinStrategy;
// import java.util.List;

// public class BoardFactory {
//     public static Board createBoard(int size) {
//         List<WinStrategy> strategies = List.of(
//             new RowWinStrategy(),
//             new ColumnWinStrategy(),
//             new DiagonalWinStrategy()
//         );
//         return new Board(size, strategies);
//     }
    
//     public static Board createDefaultBoard() {
//         return createBoard(3);
//     }
// }
