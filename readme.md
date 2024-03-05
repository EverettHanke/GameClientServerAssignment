
Simple Bank Access Protocol (SBAP)
Whenever you develop a server application, you need to specify some
application-level protocol that clients can use to interact with the server.
For the purpose of this example, we will create a "Simple Bank Access Protocol".
The table below shows the protocol format. Of course, this is just a toy
protocol to show you how to implement a server.

| Client Request | Server Response       | Description                       |
|----------------|-----------------------|-----------------------------------|
| BALANCE n      | n and the balance     | Get the balance of account n      |
| DEPOSIT n a    | n and the new balance | Deposit amount a into account n   |
| WITHDRAW n a   | n and the new balance | Withdraw amount a from account n  |
| QUIT           | Quit the connection   |                                   |

Source: Cay Horstmann, Big Java