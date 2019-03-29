SELECT noOfCopies
FROM 
tbl_book JOIN tbl_book_copies
ON tbl_book.bookId=tbl_book_copies.bookId
JOIN tbl_library_branch
ON tbl_library_branch.branchId=tbl_book_copies.branchID
WHERE title='The Lost Tribe' and branchName='Sharpstown';

SELECT noOfCopies
FROM
tbl_book JOIN tbl_book_copies
ON tbl_book.bookId=tbl_book_copies.bookId
WHERE title='The Lost Tribe';

SELECT tbl_borrower.name 
FROM 
tbl_borrower LEFT JOIN tbl_book_loans 
ON tbl_borrower.cardNo=tbl_book_loans.cardNo
WHERE tbl_borrower.cardNo is null;

SELECT title, name, address FROM
tbl_book JOIN tbl_book_loans
ON tbl_book.bookId=tbl_book.bookId 
JOIN tbl_book_borrower 
ON tbl_borrower.cardNo=tbl_book_loans.cardNo
WHERE dueDate=GETDATE();

SELECT branchName, count(bookId) 
FROM tbl_book_loans JOIN tbl_library_branch
ON tbl_book_loans.branchId=tbl_library_branch.branchId
GROUP BY branchName;

SELECT name, address, count(bookId)
FROM tbl_book_loans JOIN tbl_borrower
ON tbl_borrower.cardNo=tbl_book_loans.cardNo
GROUP BY tbl_borrower.cardNo;

SELECT title, noOfCopies
FROM tbl_book JOIN tbl_book_copies
ON tbl_book.bookId=tbl_book_copies.bookId
JOIN tbl_library_branch
ON tbl_library_branch.branchId=tbl_book_copies.branchID
WHERE branchName="Central";






