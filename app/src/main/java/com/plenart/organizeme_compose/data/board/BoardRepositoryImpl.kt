package com.plenart.organizeme_compose.data.board

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.plenart.organizeme_compose.model.Board
import kotlinx.coroutines.tasks.await

const val FIRESTORE_COLLECTION_BOARDS = "Boards"
const val BOARD_FIELD_ASSIGNED_TO = "assignedTo"

class BoardRepositoryImpl(
    private val firestore: FirebaseFirestore
) : BoardRepository {

    override suspend fun createBoard(board: Board) {                                            //potential flow!
        Log.i("BoardRepoImpl","Inside create board")
        firestore.collection(FIRESTORE_COLLECTION_BOARDS)
            .document()
            .set(board, SetOptions.merge())
            .await()
    }

    override suspend fun getBoardsAssignedToCurrentUser(userId: String): List<Board> {          //potential flow!   //potential return of (flow of?)querySnapshot and onda in viewmodel radit ovo s for petljom

        val boards = mutableListOf<Board>()

        val snapshot = firestore.collection(FIRESTORE_COLLECTION_BOARDS)
            .whereArrayContains(BOARD_FIELD_ASSIGNED_TO, userId)
            .get()
            .await()

        for(s in snapshot.documents){
            val board = s.toObject(Board::class.java)!!     //beware of !! !
            board.documentID = s.id
            boards.add(board)
        }

        return boards.toList()
    }
}
