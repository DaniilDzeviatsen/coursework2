select chapter.name,
       count(card.question) as total_questions,
       count(card.is_remembered) filter ( where is_remembered=true ) as done_elements
from chapter
         left join card on chapter.id = card.chapter_id
group by chapter.name;